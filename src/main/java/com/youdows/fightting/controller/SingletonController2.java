package com.youdows.fightting.controller;

import com.alibaba.druid.filter.AutoLoad;
import com.youdows.fightting.service.OrderService;
import com.youdows.fightting.service.impl.SingletonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by youxiaoshuang on 16/4/7.
 */
@Controller
public class SingletonController2 {
    @Autowired
    private SingletonService singletonService ;
    @Autowired
    private OrderService orderService;

//    private ThreadLocal<SingletonService> threadLocal = new ThreadLocal<SingletonService>(){
//
//        @Override
//        protected SingletonService initialValue() {
//            return singletonService;
//        }
//    };

    public void index(){
        singletonService.Num();
    }

    public void createOrderNo(){
        orderService.createOrderNO();
    }
}
