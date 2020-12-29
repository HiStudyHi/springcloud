package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 类 描 述:
 */
@Mapper
public interface OrderDao {
    int create(Order order);

    int update(@Param("userId") Long userId,@Param("status") Integer status);
}
