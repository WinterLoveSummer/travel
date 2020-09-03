package com.travel.service.scene.service.Impl;

import com.travel.service.scene.service.JestDataBaseService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.io.IOException;
import java.util.List;

/**
 * @author lhy
 * @date 2020/5/4 9:14
 * @desc: es通用操作
 */
@Service
public class JestDataBaseServiceImpl<T> implements JestDataBaseService<T> {

    private final static Logger log = LoggerFactory.getLogger(JestDataBaseServiceImpl.class);

    @Autowired
    private JestClient jestClient;

    @Override
    public boolean deleteItem(String index, String type, String id) {
        try {
            JestResult jestResult = jestClient.execute(new Delete.Builder(id).index(index).type(type).refresh(true).build());
            if (!jestResult.isSucceeded()) {
                log.error("deleteItem error:{}", jestResult.getErrorMessage());
            }
            return jestResult.isSucceeded();
        } catch (IOException e) {
            log.error("deleteItem error", e);
        }
        return false;
    }


    @Override
    public void batchIndex(String index, String type, List<T> list) {
        try {
            Bulk.Builder builder = new Bulk.Builder();
            for (T t : list) {
                builder.addAction(new Index.Builder(t).index(index).type(type).build());
            }
            JestResult jestResult = jestClient.execute(builder.build());
            if (!jestResult.isSucceeded()) {
                log.error("batchIndex error:{}", jestResult.getErrorMessage());
            }
        } catch (IOException e) {
            log.error("batchIndex error", e);
        }
    }

    @Override
    public void singleIndex(String index, String type, T t) {
        try {
            JestResult jestResult = jestClient.execute(new Index.Builder(t).index(index).type(type).build());
            if (!jestResult.isSucceeded()) {
                log.error("singleIndex error:{}", jestResult.getErrorMessage());
            }
        } catch (IOException e) {
            log.error("singleIndex error", e);
        }
    }

    @Override
    public void singleIndexWithId(String index, String type, String id, T t) {
        try {
            JestResult jestResult = jestClient.execute(new Index.Builder(t).index(index).type(type).id(id).build());
            if (!jestResult.isSucceeded()) {
                log.error("singleIndexWithId error:{}", jestResult.getErrorMessage());
            }
        } catch (IOException e) {
            log.error("singleIndexWithId error", e);
        }
    }

    @Override
    public T queryById(String index, String type, String id, Class<T> clazz) {
        T result = null;
        try {
            Get get = new Get.Builder(index, id).type(type).build();
            JestResult jestResult = jestClient.execute(get);
            result = jestResult.getSourceAsObject(clazz);
        } catch (IOException e) {
            log.error("queryById error", e);
        }
        return result;
    }

    @Override
    public <T> List<SearchResult.Hit<T, Void>> createSearch(String keyWord , String type , T o , String... fields) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery(keyWord));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        for(String field : fields){
            highlightBuilder.field(field);//高亮field
        }
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(200);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(type).build();
        SearchResult result = null ;
        List<?> hits = null ;
        try {
            result = jestClient.execute(search);
            log.info("本次查询共查到："+result.getTotal()+"个结果！");
            hits = result.getHits(o.getClass());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return (List<SearchResult.Hit<T, Void>>) hits ;
    }

}
