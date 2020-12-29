package com.atguigu.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * 类 描 述:
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException bex){
        return new CommonResult(200, "按customerBlockHandler限流测试ok，global BlockException处理----1");
    }

    public static CommonResult handlerException2(BlockException bex){
        return new CommonResult(200, "按customerBlockHandler限流测试ok，global BlockException处理-----2");
    }
}
