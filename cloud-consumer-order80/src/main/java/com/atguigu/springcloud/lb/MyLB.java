package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类 描 述: 自定义负载均衡器
 */

@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 1.Integer.MAX_VALUE signed int max value：2147483647
     * 1)重启之后重新赋值
     * 2.final修饰方法 这个方法很重要不允许其他人修改
     * 3.用到了自旋锁和CAS知识
     * 1)current期望值
     * 2)next更新值
     * 3)this.atomicInteger.compareAndSet(current,next) 更新成功返回true
     * 4.怎么得到这是第几次连接/访问:
     * 1)将这个bean加入ioc中这个bean生命周期存活
     * 2)访问一次客户端负载接口next+1
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("****第几次访问，次数:" + next);
        return next;
    }
    /**功能描述:<br>使用哪一个instance提供服务 */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
