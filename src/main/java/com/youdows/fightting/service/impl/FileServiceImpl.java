package com.youdows.fightting.service.impl;

import com.youdows.fightting.dao.NDoucumentMapper;
import com.youdows.fightting.dao.YFileMapper;
import com.youdows.fightting.entity.NDoucument;
import com.youdows.fightting.entity.NDoucumentExample;
import com.youdows.fightting.entity.YFile;
import com.youdows.fightting.service.FileService;
import com.youdows.fightting.serviceModel.FileModel;
import com.youdows.fightting.util.FileUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by youxiaoshuang on 2016/9/18.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private YFileMapper yFileMapper;
    @Autowired
    private NDoucumentMapper nDoucumentMapper;
    private Logger logger = LoggerFactory.getLogger( this.getClass() );
    private String httpPath = "http://10.0.93.65:9090/";

    public FileModel uploadLocalFile(FileModel fileModel) {
        try {
            FileUitl.toFile(fileModel.getBaseStr(),fileModel.getServerPath());
            fileModel.setUrl( httpPath+"/upload/"+fileModel.getNewName() );
        } catch (Exception e) {
            e.printStackTrace();
            logger.info( "保存图片失败:"+e );
        }
        return fileModel;
    }

    public void saveFile(FileModel fileModel) {
        //根据docCode查询文章详情
        NDoucument nDoucument = nDoucumentMapper.selectByCode( fileModel.getDocCode());
        YFile yFile = new YFile();
        yFile.setUrl( fileModel.getUrl() );
        yFile.setKey( fileModel.getKey() );
        yFile.setDocid( nDoucument.getId() );
        yFile.setFiletype( fileModel.getFileType() );
        yFile.setNewname( fileModel.getNewName() );
        yFile.setOriginalname( fileModel.getOriginalName() );
        yFile.setServerpath( fileModel.getServerPath() );
        yFile.setSize( fileModel.getSize() );
        int i = yFileMapper.insertSelective( yFile );
    }

//    public File uploadLocalFile(MultipartFile multipartFile, String savePath, String newName) {
//        try {
//            multipartFile.transferTo( new java.io.File( savePath ) );
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        YFile yFile = new YFile();
//        File file = new File();
//        yFile.setOriginalname( multipartFile.getOriginalFilename() );
//        yFile.setFiletype( multipartFile.getContentType() );
//        yFile.setNewname( newName );
//        yFile.setSize( multipartFile.getSize() );
//        yFileMapper.insertSelective( yFile );
//        BeanUtils.copyProperties( yFile, file);
//
//        return file;
//    }
}
