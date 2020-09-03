package com.travel.service.scene.config;

import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author lhy
 * @date 2020/3/16 19:02
 * @desc:
 */
@Configuration
public class FeignMultipartConfig {
    @Bean
    @Primary
    @Scope("prototype")
    public SpringFormEncoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

}
