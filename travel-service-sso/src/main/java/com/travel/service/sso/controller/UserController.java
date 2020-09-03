package com.travel.service.sso.controller;

import com.github.pagehelper.PageInfo;
import com.travel.common.Code.CommonCode;
import com.travel.common.domain.TravelUser;
import com.travel.common.dto.BaseResult;
import com.travel.common.utils.MapperUtils;
import com.travel.common.utils.StringUtils;
import com.travel.service.sso.entity.dto.TravelUserReq;
import com.travel.service.sso.service.UserService;
import com.travel.service.sso.service.consumer.RedisService;
import com.travel.service.sso.service.consumer.UploadService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@RestController
@ApiOperation(value = "用户操作相关接口")
@RequestMapping(value = "/v1/sso")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService<TravelUser> userService;

    @Autowired
    private UploadService uploadService;

    /**
     * 登录业务
     *
     * @return
     */
    @ApiOperation(value = "单点登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult login(
            @RequestBody TravelUserReq user) {
        TravelUser travelUser = userService.login(user.getLoginCode(), user.getPlantPassword());

        // 登录失败
        if (travelUser == null) {
            return BaseResult.failed(CommonCode.LOGIN_FAILED);
        }

        // 登录成功
        else {
            String token = UUID.randomUUID().toString();
            // 将 Token 放入缓存
            String result = redisService.put(token, user.getLoginCode(), 60 * 60 * 24);
            if (StringUtils.isNotBlank(result) && "put success".equals(result)) {
                //CookieUtils.setCookie(request, response, "token", token, 60 * 60 * 24);
                return BaseResult.ok((Object) token);
            }

            // 熔断处理
            else {
                return BaseResult.failed(CommonCode.ERROR);
            }

        }

    }

    /**
     * 注销
     *
     * @return
     */
    @ApiOperation(value = "注销登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public BaseResult logout(HttpServletRequest request) {
        String token = this.getToken(request);
        try {
            String res = redisService.delete(token);
            if ("delete success".equals(res)) {
                logger.debug("删除token");
                return BaseResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("注销失败");
        return BaseResult.failed();
    }

    @ApiOperation(value = "用户注册", notes = "token为空注册，不为空修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult registry(HttpServletRequest request, @RequestBody TravelUser travelUser) {
        String token = this.getToken(request);
        String loginCode = null;
        if (token != null) {
            loginCode = redisService.get(token);
        }
        //修改个人信息
        if (loginCode != null) {
            travelUser.setLoginCode(loginCode);
            int res = userService.updUser(travelUser);
            if (res > 0) {
                String put = null;
                //更新redis緩存
                try {
                    put = redisService.put(loginCode, MapperUtils.obj2json(travelUser), 60 * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ("put success".equals(put)) {
                    return BaseResult.ok();
                }
                return BaseResult.failed();
            }
        }
        //用户注册
        else {
            int res = userService.registry(travelUser);
            if (res > 0) {
                return BaseResult.ok();
            }
        }
        return BaseResult.failed(CommonCode.USER_EXIST);
    }

    @ApiOperation(value = "分页查询全部人员信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public BaseResult getUsers(
            @RequestParam(value = "pageSize", required = true) int pageSize,
            @RequestParam(value = "pageNum", required = true) int pageNum,
            @RequestBody(required = false) TravelUser travelUser) {
        PageInfo<TravelUser> page = userService.page(pageNum, pageSize, travelUser);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());
        return BaseResult.ok(page.getList(), cursor);


    }


    @ApiOperation(value = "查询个人信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public BaseResult getUser(HttpServletRequest request) {
        // 获取出请求头
        String token = this.getToken(request);
        String loginCode = redisService.get(token);
        if (loginCode == null) {
            return BaseResult.failed(CommonCode.TOKEN_FAILED);
        }
        TravelUser travelUser = userService.selectOne(loginCode);
        return BaseResult.ok(travelUser);
    }

    @ApiOperation(value = "删除个人信息")
    @RequestMapping(value = "/del", method = RequestMethod.PATCH, produces = {"application/json;charset=UTF-8"})
    public BaseResult delUser(HttpServletRequest request) {
        String token = this.getToken(request);
        String loginCode = redisService.get(token);
        int i = userService.delUser(loginCode);
        if (i > 0) {
            return BaseResult.ok();
        }
        return BaseResult.failed();
    }

    @ApiOperation(value = "通过主键获取用户信息")
    @RequestMapping(value = "/select",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public TravelUser getUser(@RequestParam("userCode") String userCode){
        return userService.selectById(userCode);
    }

    //获取token
    public String getToken(HttpServletRequest request) {
        // 获取出请求头
        String header = request.getHeader("Authorization");
        if (header == null) {
            return null;
        }
        String token = null;
        if (header.startsWith("Bearer ")) {
            token = header.substring(7);
        }
        return token;
    }



}
