package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 类 描 述:
 * 这个类有三个知识点
 * 1.RestTemplate直接使用负载均衡；使用默认轮询负载模式
 * 2.RestTemplate直接使用负载均衡；使用随机轮询负载模式
 * 3.RestTemplate使用自定义负载均衡器
 *    原理：自定义规则获取服务实例url，访问即可
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
