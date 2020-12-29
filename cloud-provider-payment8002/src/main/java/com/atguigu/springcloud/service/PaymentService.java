package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

public interface PaymentService {
    public abstract int create(Payment payment);
    public abstract Payment getPaymentById(Long id);
}
