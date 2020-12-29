package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 类 描 述:
 */
@RestController
@Slf4j
public class OrderController {
    // 地址写成服务提供者 application name
    private static final String PAYMENT_URL = "http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String create(Payment payment) {
        //restTemplate 地址 请求地址 请求参数 响应
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
    }
}
