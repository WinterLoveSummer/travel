package com.travel.common.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.travel.common.BaseDomain;
import com.travel.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Transactional(readOnly = true)
public class BaseServiceImpl<T extends BaseDomain, D extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t) {
        return dao.updateByPrimaryKey(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }
    @Override
    public PageInfo page(int pageNum, int pageSize, T t) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(dao.select(t));
        return pageInfo;
    }

    @Override
    public List list(T t) {
        return dao.select(t);
    }
}
