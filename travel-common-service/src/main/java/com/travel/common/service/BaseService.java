package com.travel.common.service;

import com.github.pagehelper.PageInfo;
import com.travel.common.BaseDomain;

import java.util.List;

public interface BaseService<T extends BaseDomain> {
    int insert(T t);

    int delete(T t);

    int update(T t);

    T selectOne(T t);

    int count(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);

    List<T> list(T t);
}
