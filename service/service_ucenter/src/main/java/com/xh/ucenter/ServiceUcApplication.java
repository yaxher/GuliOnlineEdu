package com.xh.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.xh"})
@SpringBootApplication//取消数据源自动配置
@MapperScan("com.xh.ucenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceUcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}
