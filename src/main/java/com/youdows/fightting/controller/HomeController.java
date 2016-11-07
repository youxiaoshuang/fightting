package com.youdows.fightting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youdows.fightting.entity.NDoucument;
import com.youdows.fightting.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.filter.AutoLoad;
import com.youdows.fightting.entity.YImageEntity;
import com.youdows.fightting.service.ImageService;
import com.youdows.fightting.util.PageParameter;

@Controller
@Scope("prototype")//将control设置为多例   (默认的为单例模式)
public class HomeController {
	@Autowired
	private ImageService imageService;
	@Autowired
	public NewsService newsService;
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("home/index");
		YImageEntity image = new YImageEntity();
		PageParameter parameter = new PageParameter(1, 1);
		image.setParameter(parameter);
//		List<YImageEntity> images = imageService.getImagesPage(image);
		List<NDoucument> news = newsService.findNews();
//		mav.addObject("images", images);
		mav.addObject( "news",news );
//		mav.addObject("totalpage", image.getParameter().getTotalPage());
//		mav.addObject("page",1);
		return mav;
	}

	@RequestMapping(value = "/nextPage/{page}", method = RequestMethod.GET)
	public ModelAndView newPage(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("page") String page) {
		ModelAndView mav = new ModelAndView("home/index");
		YImageEntity image = new YImageEntity();
		PageParameter parameter = new PageParameter(Integer.parseInt(page), 3);
		image.setParameter(parameter);
		List<YImageEntity> images = imageService.getImagesPage(image);
		mav.addObject("images", images);
		mav.addObject("totalpage", image.getParameter().getTotalPage());
		mav.addObject("page",page);
		return mav;
	}

}
