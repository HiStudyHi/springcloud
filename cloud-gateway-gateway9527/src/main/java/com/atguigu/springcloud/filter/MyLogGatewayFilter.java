package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 类 描 述:
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    /**功能描述:思想跟servlet的filter一样<br>
     * 1.servlet-filter：
     *  初始化（可获取配置参数）：public void  init(FilterConfig config);
     *  执行过滤（入参Request、Response）：public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain);
     *          // 把请求传回过滤链
     *         chain.doFilter(request,response);
     *  销毁filter：public void destroy();
     * 2.spring cloud gateway:
     *  类似于servlet-filter的doFilter()方法，ServerWebExchange exchange可以获取request和response
     * */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("******come in MyLogGateWayFilter:"+new Date());
        String name=exchange.getRequest().getQueryParams().getFirst("uname");
        /**功能描述:<br>uname为空 请求执行完毕 不会继续往下执行*/
        if(StringUtils.isBlank(name)){
            log.info("*******用户名为null，非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();//请求执行完毕 不会继续往下执行
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
