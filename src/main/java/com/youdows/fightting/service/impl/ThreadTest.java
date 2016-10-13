package com.youdows.fightting.service.impl;

import com.youdows.fightting.controller.SingletonController2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by youxiaoshuang on 16/4/9.
 */
public class ThreadTest implements Runnable {
    static String[] paths = {"config/spring.xml", "config/spring-mvc.xml", "config/spring-mybatis.xml"};
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(paths);
    private int num = 0;
    private String name;
    public ThreadTest(String name){
        this.name = name;
    }
    public void run() {
//        System.out.println(this.name);
//        Thread current = Thread.currentThread();
//        System.out.println(current.getId());
        SingletonController2 singletonController = context.getBean(SingletonController2.class);
        singletonController.index();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 100000; i++) {
            new Thread(new ThreadTest("Thread"+i),"线程"+i).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


