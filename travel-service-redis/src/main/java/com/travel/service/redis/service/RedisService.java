package com.travel.service.redis.service;

public interface RedisService {

    /**
     *
     * @param key
     * @param value
     * @param seconds  超时时间
     */
     void put(String key, Object value, long seconds);

     Object get(String key);

     boolean delete(Object key);
}
