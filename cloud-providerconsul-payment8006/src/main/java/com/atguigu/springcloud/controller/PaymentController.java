package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 类 描 述:
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("payment/consul")
    public String paymentzk() {
        return "spring cloud with consul, server port:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
