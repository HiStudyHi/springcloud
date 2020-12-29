package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 类 描 述:
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        getList();
        return "testA-----";
    }

    @GetMapping("/testB")
    public String testB() {
        getList();
        return "testB   -----";
    }

    @GetMapping("/testQPS")
    public String testQPS() {
        return "testQPS-----";
    }

    @GetMapping("/testThread")
    public String testThread() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testThread-----";
    }

    /*流控模式 链路模式*/
    @SentinelResource("getList()")
    public String getList() {
        log.info("=====list=====");
        return "list";
    }

    /*流控效果 排队 核心漏斗算法*/
    @GetMapping("/testC")
    public String getC() {
        log.info("当前线程: " + Thread.currentThread().getName());
        return "C-----------";
    }

    /*服务熔断降级 RT*/
    @GetMapping("/testD")
    public String getD() {
        try {
            TimeUnit.SECONDS.sleep(1l);
        } catch (Exception ex) {

        }
        return "D测试RT-----------";
    }

    /*服务熔断降级 异常率=异常请求/请求总数*/
    @GetMapping("/testE")
    public String getE() {
        int a = 10 / 0;
        return "E测试 异常率-----------";
    }

    /*服务熔断降级 异常数*/
    @GetMapping("/testF")
    public String getF() {
        int a = 10 / 0;
        return "F测试 异常数-----------";
    }

    @GetMapping("/testHotKey")
    //blockHandler 触发热点参数限流规则之后调用的处理方法
    @SentinelResource(value = "testHotKey", blockHandler = "dealTestHostKey")
    public String testHotKey(@RequestParam(required = false) String p1,
                             @RequestParam(required = false) String p2) {
        return "testHotKey==============";
    }

    public String dealTestHostKey(String p1, String p2, BlockException bex) {
        return "dealTestHostKey==============完蛋";//替代sentinel系统的默认提示
    }
}
