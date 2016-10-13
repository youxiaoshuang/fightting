package com.youdows.fightting.service.impl;

import com.youdows.fightting.controller.SingletonController2;
import com.youdows.fightting.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by youxiaoshuang on 16/4/10.
 */
public class OrderTest implements Runnable {
    static String[] paths = {"config/spring.xml", "config/spring-mvc.xml", "config/spring-mybatis.xml"};
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(paths);
    public static void main(String[] args){
        for (int i = 0; i <100 ; i++) {
            new Thread(new OrderTest(), "线程" + i).start();
        }

    }

    public void run() {
        SingletonController2 orderService = context.getBean(SingletonController2.class);
        orderService.createOrderNo();
    }
}
