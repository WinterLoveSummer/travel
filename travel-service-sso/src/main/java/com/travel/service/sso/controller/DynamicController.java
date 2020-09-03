package com.travel.service.sso.controller;

import com.github.pagehelper.PageInfo;
import com.travel.common.Code.CommonCode;
import com.travel.common.dto.BaseResult;
import com.travel.common.utils.StringUtils;
import com.travel.service.sso.entity.dto.DynamicReq;
import com.travel.service.sso.service.DynamicService;
import com.travel.service.sso.service.consumer.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lhy
 * @date 2020/5/4 13:42
 * @desc:
 */

@RestController
@ApiOperation("论坛相关接口")
@RequestMapping(value = "/v1/dynamic")
public class DynamicController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DynamicService dynamicService;

    @ApiOperation("新增论坛信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public BaseResult addDynamic(@RequestBody DynamicReq dynamicReq,HttpServletRequest request){
        if(StringUtils.isEmpty(dynamicReq.getTitle())){
            return BaseResult.ok("标题不能为空");
        }
        if(StringUtils.isEmpty(dynamicReq.getMain())){
            return BaseResult.ok("内容不能为空");
        }
        String token = getToken(request);
        String loginCode = redisService.get(token);
        if(StringUtils.isEmpty(loginCode)){
            return BaseResult.failed();
        }
        int number = dynamicService.addDynamic(dynamicReq, loginCode);
        if(number>0){
            return BaseResult.ok("保存成功");
        }
        return BaseResult.failed();
    }

    @ApiOperation("按条件按分页获取论坛信息")
    @RequestMapping(value = "/Page",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult dynamicList(@RequestParam(value = "loginCode",required = false) String loginCode,
                                  @RequestParam("pageNum") int pageNum,
                                  @RequestParam("pageSize") int pageSize){

        PageInfo page = dynamicService.getDynamics(pageNum,pageSize,loginCode);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());
        return BaseResult.ok(page.getList(),cursor);
    }




    /**
     * 获取token
     * @param request
     * @return
     */
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
