package com.Alibaba.ai.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class OrderTools {

    @Autowired
    private OrderService orderService;

    // 接收参数
    public record GetOrderRequest(String userName) {}

    @Bean
    @Description("获取订单")
    public Function<GetOrderRequest,String> getOrder(){
        return new Function<GetOrderRequest, String>() {
            @Override
            public String apply(GetOrderRequest getOrderRequest) {
                return orderService.getOrder(getOrderRequest.userName);
            }
        };
    }

    // 接收参数2
    public record DeleteOrderRequest(String userName, String orderName) {}

    @Bean
    @Description("删除订单")
    public Function<DeleteOrderRequest,String> deleteOrder(){
        return new Function<DeleteOrderRequest, String>() {
            @Override
            public String apply(DeleteOrderRequest deleteOrderRequest) {
                return orderService.deleteOrder(deleteOrderRequest.userName, deleteOrderRequest.orderName);
            }
        };
    }
}
