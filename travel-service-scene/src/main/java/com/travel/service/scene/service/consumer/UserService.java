package com.travel.service.scene.service.consumer;

import com.travel.common.domain.TravelUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lhy
 * @date 2020/3/22 19:16
 * @desc:
 */
@Component
@FeignClient(value = "travel-service-sso")
@RequestMapping(value = "/v1/sso")
public interface UserService {
    @RequestMapping(value = "/select", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    TravelUser getUser(@RequestParam("userCode") String userCode);
}
