package com.travel.service.sso.service;

import com.github.pagehelper.PageInfo;
import com.travel.service.sso.entity.TravelDynamic;
import com.travel.service.sso.entity.dto.DynamicReq;
import com.travel.service.sso.entity.vo.DynamicVo;

/**
 * @author lhy
 * @date 2020/5/4 13:42
 * @desc:
 */
public interface DynamicService {

    /**
     * 新增论坛信息
     * @param dynamicReq
     * @return
     */
    int addDynamic(DynamicReq dynamicReq,String loginCode);

    PageInfo<DynamicVo> getDynamics(int pageNum, int pageSize, String loginCode);
}
