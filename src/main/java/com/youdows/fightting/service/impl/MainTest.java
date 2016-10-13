package com.youdows.fightting.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by youxiaoshuang on 16/4/5.
 */
public class MainTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config/spring.xml", "config/spring-mvc.xml", "config/spring-mybatis.xml"});
        TransnationTest3 transnationTest  = (TransnationTest3) context.getBean(TransnationTest3.class);
        transnationTest.trandationTest1();
    }
}
