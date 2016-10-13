package com.youdows.fightting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.youdows.fightting.entity.UserEntity;
import com.youdows.fightting.service.UserService;
//@Scope("prototype")//单列模式
@Controller
@RequestMapping("/test")
public class TestController{
	private static Logger logger = Logger.getLogger(TestController.class);
	@Autowired
    private UserService uservice;
 /**
  * 测试方法,HelloWord
  * @param request
  * @param response
  * @return
  * @throws Exception
  */
 @RequestMapping(value="/list/{name}/{password}",method=RequestMethod.GET)
    public ModelAndView getProducts(HttpServletRequest request,HttpServletResponse response,UserEntity user) throws Exception {
	 ModelAndView mav = new ModelAndView("test/test");
	    request.setAttribute("name", "helloWord");
        logger.info(user.getName());
        uservice.saveUser(user);
        mav.addObject("id", user.getId());
        return mav;
    }
}