package com.travel.service.sso.service;

import com.travel.common.BaseDomain;
import com.travel.common.domain.TravelUser;
import com.travel.common.service.BaseService;

public interface UserService<T extends BaseDomain> extends BaseService<T> {

    /**
     * 登录
     * @param loginName
     * @param planPassword
     * @return
     */
    TravelUser login(String loginName, String planPassword);


    /**
     * 注册
     * @param travelUser
     * @return
     */
    int registry(TravelUser travelUser);

    /**
     * 修改用户信息
     * @param travelUser
     * @return
     */
    int updUser(TravelUser travelUser);

    TravelUser selectOne(String loginCode);

    int delUser(String userCode);

    /**
     * 通过主键获取用户信息
     * @param userCode
     * @return
     */
    TravelUser selectById(String userCode);
}
