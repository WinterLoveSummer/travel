package com.travel.service.scene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.travel")
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients
@MapperScan(basePackages = "com.travel.service.scene.mapper")
public class SceneApplication {
    public static void main(String[] args) {
        SpringApplication.run(SceneApplication.class, args);
    }
}
