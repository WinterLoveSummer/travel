package com.travel.service.scene.mapper;

import com.travel.service.scene.entity.TravelScene;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TravelSceneMapper extends MyMapper<TravelScene> {

    List<TravelScene> selectSlideshow();

    /**
     * 评论数量加
     * @param id
     * @return
     */
    int updatecommentCount(@Param("id") Integer id);
}