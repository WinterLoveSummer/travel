package com.travel.service.sso.mapper;

import com.travel.common.domain.TravelUser;
import com.travel.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
@CacheNamespace(implementation = RedisCache.class)
public interface TravelUserMapper extends MyMapper<TravelUser> {

    int updateStatus(@Param(value = "loginCode") String loginCode);

}