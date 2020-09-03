package com.travel.service.scene.service;

import io.searchbox.core.SearchResult;

import java.util.List;

/**
 * @author lhy
 * @date 2020/5/4 9:12
 * @desc: es数据的操作
 */
public interface JestDataBaseService<T> {

    /**
     * 单条删除
     */
    boolean deleteItem(String index, String type, String id);


    /**
     * 批量创建索引
     */
    void batchIndex(String index, String type, List<T> list);

    /**
     * 单条索引(新增/更新)
     */
    void singleIndex(String index, String type, T t);

    /**
     * 指定索引ID
     */
    void singleIndexWithId(String index, String type, String id, T t);

    /**
     * 根据id查询
     */
    T queryById(String index, String type, String id, Class<T> clazz);

    /**
     * 搜索
     * @return
     */
    <T> List<SearchResult.Hit<T,Void>> createSearch(String keyWord , String type , T o , String... fields);

}

