package com.travel.service.scene.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "travel-service-redis")
public interface RedisService {

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") long seconds);


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    String get(@RequestParam(value = "key") String key);

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    String delete(@RequestParam(value = "key") String key);
}
