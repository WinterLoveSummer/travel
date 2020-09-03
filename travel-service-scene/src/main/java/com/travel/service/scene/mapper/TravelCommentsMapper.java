package com.travel.service.scene.mapper;

import com.travel.service.scene.entity.TravelComments;
import com.travel.service.scene.entity.vo.CommentsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TravelCommentsMapper extends MyMapper<TravelComments> {

    int insertComments(@Param("travelComments") TravelComments travelComments);

    List<CommentsVo> selectComments(@Param("sceneId") Integer sceneId);
}