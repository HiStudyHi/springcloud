package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 类 描 述:
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功，serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败，serverPort:" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果" + payment);
        if (Objects.nonNull(payment)) {
            return new CommonResult(200, "查询数据成功，serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询id：" + id, null);
        }
    }

    /**
     * 功能描述:<br>
     * application.name 对应 servicename
     * instance.instance-id  对应 serviceid
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        //spring.application.name ！！！不仅仅是这一个微服务的application.name而是整个Eureka中的所有微服务的application.name
        List<String> serviceList = discoveryClient.getServices();
        serviceList.forEach(service -> {
            log.info("*****" + service);
        });
        //指定微服务名称application.name,service-id就是eureka.instance.instance-id
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        serviceInstanceList.forEach(serviceInstance -> {
            log.info(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        });
        return discoveryClient;
    }
    /**功能描述:<br>Ribbon负载均衡*/
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
