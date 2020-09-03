package com.travel.service.scene.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.BaseDomain;
import com.travel.common.Code.CommonCode;
import com.travel.common.domain.TravelUser;
import com.travel.common.dto.BaseResult;
import com.travel.common.utils.MapperUtils;
import com.travel.common.utils.StringUtils;
import com.travel.service.scene.entity.TravelScene;
import com.travel.service.scene.entity.dto.CommentsDto;
import com.travel.service.scene.entity.vo.CommentsVo;
import com.travel.service.scene.service.SceneService;
import com.travel.service.scene.service.consumer.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/scene")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "初始化旅游景点信息")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResult addScene(@RequestParam("fileDir") int fileDir,
                               @RequestParam("pageNum") int pageNum,
                               @RequestParam("city") String city
    ){
        int i = sceneService.insertScene(fileDir,pageNum,city);
        if(i>0){
            return BaseResult.ok();
        }
        return BaseResult.failed();
    }


//    @ApiOperation(value = "分页查询景点信息")
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public BaseResult findScenes(
//            @RequestParam(value = "pageSize") int pageSize,
//            @RequestParam(value = "pageNum") int pageNum,
//            @RequestBody(required = false) TravelScene travelScene
//            ){
//        PageInfo page = sceneService.page(pageNum, pageSize, travelScene);
//        BaseResult.Cursor cursor = new BaseResult.Cursor();
//        cursor.setTotal(new Long(page.getTotal()).intValue());
//        cursor.setLimit(page.getPageSize());
//        cursor.setOffset(page.getPageNum());
//        return BaseResult.ok(page.getList(),cursor);
//    }

    @ApiOperation(value = "按城市名称分页查询景点信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public BaseResult findSceneList(
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "city",required = false) String city
            ){
        PageInfo page = sceneService.selectScenesByCity(pageNum,pageSize,city);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());
        return BaseResult.ok(page.getList(),cursor);
    }

    @ApiOperation(value = "首页数据")
    @RequestMapping(value = "/home",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult findHoneScenes(){
        List homeData = sceneService.getHomeData();
        return BaseResult.ok(homeData);
    }

    @ApiOperation(value = "获取详情信息")
    @RequestMapping(value = "/details",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult getDetails(@RequestParam(value = "id") Integer id){
        TravelScene details = sceneService.getDetails(id);
        return BaseResult.ok(details);
    }

    @ApiOperation(value = "获取轮播图数据")
    @RequestMapping(value = "/slideshow",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult getSlideshow(){
        List<TravelScene> slideshow = sceneService.getSlideshow();
        return BaseResult.ok(slideshow);
    }

    @ApiOperation(value = "分页获取景点评论信息")
    @RequestMapping(value = "/comments",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult getComment(@RequestParam("pageNum") int pageNum,
                                 @RequestParam("pageSize") int pageSize,
                                 @RequestParam(value = "sceneId") Integer sceneId){
        PageInfo<CommentsVo> page = sceneService.getComments(pageNum,pageSize,sceneId);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());
        return BaseResult.ok(page.getList(),cursor);
    }


    @ApiOperation(value = "新增评论")
    @RequestMapping(value = "/addComments",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public BaseResult setComments(@RequestBody CommentsDto commentsDto, HttpServletRequest request){
        String token = getToken(request);
        if(StringUtils.isNotBlank(token)){
            String loginCode = redisService.get(token);
            if(loginCode != null){
                String s = redisService.get(loginCode);
                TravelUser travelUser = null;
                try {
                    travelUser = MapperUtils.json2pojo(s, TravelUser.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int i = sceneService.setComments(commentsDto, travelUser);
                if(i>0){
                    return BaseResult.ok();
                }
            }
        }
        return BaseResult.failed(CommonCode.TOKEN_FAILED);
    }

    @ApiOperation("推荐功能接口")
    @RequestMapping(value = "/recommend",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult recommend(HttpServletRequest request){
        //获取用户信息
        String token = getToken(request);
//        String token = "fa51bce2-04c2-41fa-9a68-bbaba232b54d";
        if(StringUtils.isNotBlank(token)){
            String loginCode = redisService.get(token);
            if(StringUtils.isEmpty(loginCode)){
                return BaseResult.failed(CommonCode.USER_LOGOUT);
            }
            if(loginCode != null) {
                String s = redisService.get(loginCode);
                TravelUser travelUser = null;
                try {
                    travelUser = MapperUtils.json2pojo(s, TravelUser.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<TravelScene> recommend = sceneService.getRecommend(travelUser);
                return BaseResult.ok(recommend);
            }
        }
        return BaseResult.failed();
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
