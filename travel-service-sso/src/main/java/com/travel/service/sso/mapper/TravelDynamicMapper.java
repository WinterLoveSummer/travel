package com.travel.service.sso.mapper;

import com.travel.service.sso.entity.TravelDynamic;
import com.travel.service.sso.entity.vo.DynamicVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TravelDynamicMapper extends MyMapper<TravelDynamic> {

    List<DynamicVo> selectDynamics(@Param("loginCode") String loginCode);
}