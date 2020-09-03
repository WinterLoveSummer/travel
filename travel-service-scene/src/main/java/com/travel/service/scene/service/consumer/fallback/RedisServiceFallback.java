package com.travel.service.scene.service.consumer.fallback;

import com.travel.common.hystrix.Fallback;
import com.travel.service.scene.service.consumer.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {

    @Override
    public String put(String key, String value, long seconds) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String key) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String key) {
        return Fallback.badGateway();
    }
}
