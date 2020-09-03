package com.travel.service.scene.service;

import com.github.pagehelper.PageInfo;
import com.travel.common.BaseDomain;
import com.travel.common.domain.TravelUser;
import com.travel.common.service.BaseService;
import com.travel.service.scene.entity.TravelComments;
import com.travel.service.scene.entity.TravelScene;
import com.travel.service.scene.entity.dto.CommentsDto;
import com.travel.service.scene.entity.vo.CommentsVo;
import com.travel.service.scene.entity.vo.HomeScenes;

import java.util.List;

public interface SceneService<T extends BaseDomain> extends BaseService<T> {


    /**
     * 将景点信息存入到数据库
     * @return
     */
    int insertScene(int fileDir,int pageNum,String city);

    /**
     * 按城市查询景点信息
     * @param city
     * @return
     */
     PageInfo selectScenesByCity(int pageNum, int pageSize, String city);


    /**
     * 首页数据
     * @return
     */
     List<HomeScenes> getHomeData();

    /**
     * 获取详情信息
     * @param id
     * @return
     */
     TravelScene getDetails(Integer id);

    /**
     * 轮播图数据
     * @return
     */
    List<TravelScene> getSlideshow();


    /**
     * 获取景点评论信息
     * @param sceneId
     * @return
     */
    PageInfo<CommentsVo> getComments(int pageNum,int pageSize,Integer sceneId);

    /**
     * 新增评论信息
     * @return
     */
    int setComments(CommentsDto commentsDto, TravelUser travelUser);

    /**
     * 获取全部景点信息
     * @return
     */
    List<TravelScene> getScenes();


    /**
     * 获取推荐景点信息
     * @param travelUser
     * @return
     */
    List<TravelScene> getRecommend(TravelUser travelUser);


}
