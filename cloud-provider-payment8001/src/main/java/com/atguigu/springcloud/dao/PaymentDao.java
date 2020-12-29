package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 推荐使用@Mapper 使用@Repository会有问题
 */
@Mapper
public interface PaymentDao {
    public abstract int create(Payment payment);

    public abstract Payment getPaymentById(@Param("id") Long id);
}
