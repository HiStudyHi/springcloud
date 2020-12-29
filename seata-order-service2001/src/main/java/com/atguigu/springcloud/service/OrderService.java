package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.Order;

/**
 * 类 描 述:
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
