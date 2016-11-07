package com.youdows.fightting.controller;

import com.youdows.fightting.entity.YFile;
import com.youdows.fightting.service.FileService;
import com.youdows.fightting.serviceModel.FileModel;
import com.youdows.fightting.util.FileUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    @Value("#{sys['filePath']}")
    private String fileDir;

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
        String filePath = fileDir + "/" + saveName + fileModel.getFileType();
        fileModel.setNewName( saveName + fileModel.getFileType() );
        fileModel.setServerPath( filePath );
        fileModel.setKey( saveName );
        fileModel.setSize( fileModel.getBaseStr().getBytes().length );
        fileModel = fileService.uploadLocalFile( fileModel );
        //保存文件信息
        fileService.saveFile( fileModel );
        return fileModel;
    }

    @RequestMapping("/getFile/{key}")
    public void getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable String key) throws IOException {
        //根据key获取图片信息
        YFile fileByKey = fileService.getFileByKey( key );
        if (fileByKey != null) {
            String filePath = fileByKey.getServerpath();
            byte[] bytes = FileUitl.getBytes( filePath );
            InputStream buffin = new ByteArrayInputStream( bytes );
            BufferedImage img;
            response.setContentType("image/jpeg");
            OutputStream output = response.getOutputStream();
            InputStream in = new ByteArrayInputStream(bytes);
            int len;
            byte[] buf = new byte[1024];
            while ((len = in.read(buf)) != -1) {
                output.write(buf, 0, len);
            }
            output.flush();

        }

    }


}
