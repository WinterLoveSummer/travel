package com.travel.service.redis.controller;

import com.travel.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {


    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") long seconds){
        redisService.put(key, value, seconds);
        return "put success";
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key){
        Object o = redisService.get(key);
        if(o != null){
            return String.valueOf(o);
        }

        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "key") String key){
        boolean delete = redisService.delete(key);
        if(delete){
            return "delete success";
        }
        return null;
    }
}
