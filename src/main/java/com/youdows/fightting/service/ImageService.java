package com.youdows.fightting.service;

import io.netty.handler.codec.http.HttpResponse;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.youdows.fightting.entity.YImageEntity;

public interface ImageService {
	boolean insertImage(YImageEntity image);
	List<YImageEntity> getImagesPage(YImageEntity image);
	boolean uploadImage(MultipartFile file);
	void getImage(String imageName,HttpServletResponse response);
	String getImageFtpUrlByName(String imageName);

}
