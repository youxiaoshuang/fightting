package com.youdows.fightting.controller;

import com.youdows.fightting.service.FileService;
import com.youdows.fightting.serviceModel.FileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传服务control
 * Created by youxiaoshuang on 2016/9/18.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Controller
@RequestMapping("/fileCenter")
public class FileController {
    @Autowired
    private FileService fileService;
    private Logger logger = LoggerFactory.getLogger( FileController.class );

    /**
     * 文件上传
     *
     * @param request
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public FileModel fileUpload(HttpServletRequest request, FileModel fileModel) throws IOException {
        // 文件保存路径
        String saveName = UUID.randomUUID().toString().replace( "-", "" );
        String filePath = request.getSession().getServletContext().getRealPath( "/" ) + "upload/"
                + saveName + fileModel.getFileType();
        fileModel.setNewName( saveName + fileModel.getFileType() );
        fileModel.setServerPath( filePath );
        fileModel.setKey( saveName );
        fileModel.setSize( fileModel.getBaseStr().getBytes().length );
        fileModel = fileService.uploadLocalFile( fileModel );
        //保存文件信息
        fileService.saveFile( fileModel );
        return fileModel;
    }




}
