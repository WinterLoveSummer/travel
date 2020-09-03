package com.travel.service.scene.mapper;

import com.travel.common.domain.TravelUser;
import com.travel.service.scene.entity.TravelRecommend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TravelRecommendMapper extends MyMapper<TravelRecommend> {
    List<TravelRecommend> selectRecommend(@Param("travelUser") TravelUser travelUser);
}