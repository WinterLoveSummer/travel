package com.travel.service.scene.service;

import com.travel.service.scene.entity.TravelScene;

import java.util.List;

/**
 * @author lhy
 * @date 2020/5/4 9:08
 * @desc:
 */
public interface SearchService {

    /**
     * 将景点信息初始化到es
     * @return
     */
    void initElasticSearch(String index, String type, List<TravelScene> scenes);

    /**
     * 搜索景点信息
     * @param keys
     * @param token
     * @return
     */
    List<TravelScene> searchScene(String keys,String token);
}
