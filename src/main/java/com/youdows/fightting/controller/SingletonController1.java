package com.youdows.fightting.controller;

import com.alibaba.druid.filter.AutoLoad;
import com.youdows.fightting.service.impl.SingletonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 解决高并发spring问题  第一方式:将controller设置为多例@Scope("prototype")
 * Created by youxiaoshuang on 16/4/7.
 */
@Controller
@Scope("prototype")
public class SingletonController1 {
    @Autowired
    private SingletonService singletonService;

    public void index(){
        singletonService.Num();
    }

}
