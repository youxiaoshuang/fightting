package com.youdows.fightting.controller;
import com.youdows.fightting.entity.NDoucument;
import com.youdows.fightting.service.NewsService;
import com.youdows.fightting.util.JsonTool;
import net.sf.json.JSON;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * Created by youxiaoshuang on 16/7/27.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    public NewsService newsService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("news/newsEditIndex");
        //初始化一个新闻
        NDoucument nDoucument = newsService.addDefaultNews();
        mav.addObject( "docCode",nDoucument.getUuid() );
        return mav;
    }

    @RequestMapping(value = "/subNews",method = RequestMethod.POST)
    @ResponseBody
    public NDoucument subNews(NDoucument nDoucument){
        logger.info("传入新闻{}",nDoucument.getContent().toString());
        ModelAndView mav = new ModelAndView("news/newsEditIndex");
        nDoucument = newsService.addNews(nDoucument);
        logger.info("插入返回{}", JsonTool.writeValueAsString(nDoucument));
        return nDoucument;
    }

    @RequestMapping(value = "/newShowMyself")
    public ModelAndView showNews(){
        ModelAndView modelAndView = new ModelAndView("news/newShowMyself");
        List<NDoucument> news = newsService.findNews();
        logger.info("返回的新闻条数{}",news.size());
        modelAndView.addObject("news",news);
        return modelAndView;
    }

    /**
     * 获取文章详情
     * @param code
     * @return
     */
    @RequestMapping(value = "/getNewsDetail")
    public ModelAndView getNewsDetail(@Param( "code" ) String code){
        ModelAndView modelAndView = new ModelAndView( "news/newDetail" );
        NDoucument nDoucument = newsService.getNewsDetailByUuid( code );
        modelAndView.addObject( "nDoucument",nDoucument );
        return modelAndView;
    }


}
