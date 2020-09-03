package com.travel.service.scene.controller;

import com.google.gson.JsonObject;
import com.travel.common.domain.TravelUser;
import com.travel.common.dto.BaseResult;
import com.travel.common.utils.MapperUtils;
import com.travel.service.scene.entity.TravelScene;
import com.travel.service.scene.service.SceneService;
import com.travel.service.scene.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lhy
 * @date 2020/5/3 20:57
 * @desc: 景点搜索接口
 */
@RestController
@RequestMapping(value = "/v1/search")
public class SearchController {

    @Autowired
    private SceneService sceneService;

    @Autowired
    private SearchService searchService;

    @ApiOperation("景点搜索")
    @RequestMapping(value = "/scene",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public BaseResult searchScene(@RequestParam(value = "keys") String keys,HttpServletRequest request){
        String token = getToken(request);
//        String token = "28055fbb-5a3c-4558-b362-acd2789cf41e";
        List<TravelScene> scenes = searchService.searchScene(keys,token);
        return BaseResult.ok(scenes);
    }


    @ApiOperation("初始化景点到ES")
    @RequestMapping(value = "/init",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public BaseResult initES(){
        searchService.initElasticSearch("travel","travel_scene",sceneService.getScenes());
        return BaseResult.ok();
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
