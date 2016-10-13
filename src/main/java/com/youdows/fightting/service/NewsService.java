package com.youdows.fightting.service;

import com.youdows.fightting.entity.NDoucument;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by youxiaoshuang on 16/7/28.
 * Projiect fightting
 * Author youxiaoshuang
 */
public interface NewsService {
    public NDoucument addNews(NDoucument nDoucument);



    List<NDoucument> findNews();



    NDoucument addDefaultNews();

    NDoucument getNewsDetailByUuid(String code);
}
