package com.travel.service.sso;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication(scanBasePackages = "com.travel")
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients
@MapperScan(basePackages = "com.travel.service.sso.mapper")
public class SSOApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class,args);
    }
}
