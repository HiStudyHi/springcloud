package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 类 描 述:80端口微服务需要调用8001端口微服务；使用restTemplate
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced//自定义客户端负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
