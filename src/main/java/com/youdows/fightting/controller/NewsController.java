package com.youdows.fightting.controller;

import com.youdows.fightting.common.Response;
import com.youdows.fightting.entity.NDoucument;
import com.youdows.fightting.entity.Tag;
import com.youdows.fightting.service.NewsService;
import com.youdows.fightting.service.TagService;
import com.youdows.fightting.util.JsonTool;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    public TagService tagService;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView( "news/newsEditIndex" );
        //初始化一个新闻
        NDoucument nDoucument = newsService.addDefaultNews();
        mav.addObject( "docCode", nDoucument.getUuid() );
        return mav;
    }

    @RequestMapping(value = "/subNews", method = RequestMethod.POST)
    @ResponseBody
    public NDoucument subNews(NDoucument nDoucument) {
        logger.info( "传入新闻{}", nDoucument.getContent().toString() );
        ModelAndView mav = new ModelAndView( "news/newsEditIndex" );
        nDoucument = newsService.addNews( nDoucument );
        logger.info( "插入返回{}", JsonTool.writeValueAsString( nDoucument ) );
        return nDoucument;
    }

    @RequestMapping(value = "/newShowMyself")
    public ModelAndView showNews() {
        ModelAndView modelAndView = new ModelAndView( "news/newShowMyself" );
        List<NDoucument> news = newsService.findNews();
        logger.info( "返回的新闻条数{}", news.size() );
        modelAndView.addObject( "news", news );
        return modelAndView;
    }

    /**
     * 获取文章详情
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/getNewsDetail")
    public ModelAndView getNewsDetail(@Param("code") String code) {
        ModelAndView modelAndView = new ModelAndView( "news/newDetail" );
        NDoucument nDoucument = newsService.getNewsDetailByUuid( code );
        modelAndView.addObject( "nDoucument", nDoucument );
        return modelAndView;
    }

    @RequestMapping(value = "/getTags",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTags(HttpServletResponse response) throws IOException {
        List<Tag> tags = tagService.getTags();
        int length = tags.size();
        String[] strings = new String[length];
        for (int i = 0; i <= length - 1; i++) {
            strings[i] = tags.get( i ).getTagname();
        }
        return JsonTool.writeValueAsString( strings );
    }

    /**
     * 新增标签
     *
     * @param tag
     * @return
     */
    @RequestMapping("/addTag")
    @ResponseBody
    public Response addTag(Tag tag) {
        Response response = new Response();
        Tag tag1 = tagService.addTag( tag );
        response.setData( tag1 );
        response.setMsg( "success" );
        response.setStatus( 1 );
        return response;
    }


}
