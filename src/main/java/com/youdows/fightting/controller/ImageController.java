package com.youdows.fightting.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.youdows.fightting.service.ImageService;

@Controller
@RequestMapping("/images")
public class ImageController {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ImageService imageService;
	

	@RequestMapping(value = "/{imageName}/{imageType}", method = RequestMethod.GET)
	public void getImages(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("imageName") String imageName,
			@PathVariable("imageType") String imageType) {
		logger.info("获取图片" + imageName + "." + imageType+"开始");

		// HttpSession seesion = request.getSession();
		// Photo photo = photoService.getPhotoById(new BigDecimal(id));
		// byte[] data = photo.getPhotoData();
		String imagePath = imageService.getImageFtpUrlByName(imageName);
		if(imagePath != null){
		   imageService.getImage(imagePath, response);
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public void fileUpload(HttpServletRequest request) throws IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				imageService.uploadImage(file);
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				logger.info(file.getName() + "上传消耗时间" + (finaltime - pre));
			}
		}

	}
	

}
