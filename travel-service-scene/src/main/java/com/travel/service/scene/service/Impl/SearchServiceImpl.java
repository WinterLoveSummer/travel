package com.travel.service.scene.service.Impl;

import com.travel.common.domain.TravelUser;
import com.travel.common.utils.MapperUtils;
import com.travel.common.utils.StringUtils;
import com.travel.service.scene.entity.TravelRecommend;
import com.travel.service.scene.entity.TravelScene;
import com.travel.service.scene.mapper.TravelRecommendMapper;
import com.travel.service.scene.service.JestDataBaseService;
import com.travel.service.scene.service.SearchService;
import com.travel.service.scene.service.consumer.RedisService;
import io.searchbox.core.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhy
 * @date 2020/5/4 9:09
 * @desc:
 */
@Service
public class SearchServiceImpl implements SearchService {

    private final static Logger log = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private JestDataBaseService jestDataBaseService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TravelRecommendMapper travelRecommendMapper;

    @Override
    public List<TravelScene> searchScene(String keys,String token){
        List<TravelScene> travelScenes = new ArrayList<>();
        List<SearchResult.Hit<TravelScene, Void>> hits = jestDataBaseService.createSearch(keys, "travel", new TravelScene(), "interest");
        for (SearchResult.Hit<TravelScene, Void> hit : hits) {
            TravelScene travelScene = hit.source;
            travelScenes.add(travelScene);
        }
        //token不为空保存用户搜索内容到推荐表
        if(token != null) {
            String loginCode = redisService.get(token);
            if (loginCode != null) {
                String s = redisService.get(loginCode);
                TravelUser travelUser = null;
                try {
                    travelUser = MapperUtils.json2pojo(s, TravelUser.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                addRecommend(travelScenes.get(0),travelUser);
            }
        }
        return travelScenes;
    }

    /**
     * 推荐信息存入数据库
     * @param travelScene
     * @param travelUser
     * @return
     */
    public int addRecommend(TravelScene travelScene,TravelUser travelUser){
        Example example = new Example(TravelRecommend.class);
        example.createCriteria().andEqualTo("sceneId",travelScene.getId())
                .andEqualTo("loginCode",travelUser.getLoginCode());
        List<TravelRecommend> travelRecommends = travelRecommendMapper.selectByExample(example);
        if(travelRecommends != null && travelRecommends.size() > 0){
            log.info("recommend is exist");
            return -1;
        }
        TravelRecommend travelRecommend = new TravelRecommend();
        travelRecommend.setSceneId(travelScene.getId());
        travelRecommend.setWeight(10);
        travelRecommend.setSchool(travelUser.getSchool());
        travelRecommend.setOffice(travelUser.getOffice());
        travelRecommend.setDateBirth(travelUser.getDateBirth());
        travelRecommend.setLoginCode(travelUser.getLoginCode());
        int insert = travelRecommendMapper.insert(travelRecommend);
        return insert;
    }


    @Override
    public void initElasticSearch(String index, String type, List<TravelScene> scenes) {
        jestDataBaseService.batchIndex(index,type,scenes);
    }
}
