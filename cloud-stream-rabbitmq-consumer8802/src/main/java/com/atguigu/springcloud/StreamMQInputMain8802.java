package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 类 描 述:
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQInputMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQInputMain8802.class, args);
    }
}
