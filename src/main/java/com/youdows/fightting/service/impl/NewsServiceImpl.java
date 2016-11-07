package com.youdows.fightting.service.impl;

import com.youdows.fightting.dao.NDoucumentMapper;
import com.youdows.fightting.dao.YFileMapper;
import com.youdows.fightting.dto.Page;
import com.youdows.fightting.entity.NDoucument;
import com.youdows.fightting.entity.YFile;
import com.youdows.fightting.service.NewsService;
import com.youdows.fightting.util.PageParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by youxiaoshuang on 16/7/28.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NDoucumentMapper nDoucumentMapper;
    @Autowired
    private YFileMapper yFileMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public NDoucument addNews(NDoucument nDoucument) {
        String uuId = UUID.randomUUID().toString().replace("-","");
        nDoucument.setStatus(1);
        nDoucument.setCreatetime(new Date());
        nDoucumentMapper.updateByUuid( nDoucument);
        return nDoucument;
    }

    public List<NDoucument> findNews() {
        PageParameter pageParameter = new PageParameter(1, 20);
        Page page = new Page();
        page.setParameter(pageParameter);
        List<NDoucument> nDoucuments = nDoucumentMapper.selectByAllPage(page);
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
        }
        return nDoucuments;
    }

    public NDoucument addDefaultNews() {
        String uuId = UUID.randomUUID().toString().replace("-","");
        NDoucument nDoucument = new NDoucument();
        nDoucument.setStatus(0);
        nDoucument.setUuid(uuId);
        nDoucumentMapper.insertSelective(nDoucument);
        return nDoucument;
    }

    public NDoucument getNewsDetailByUuid(String code) {
        NDoucument nDoucument = nDoucumentMapper.selectByCode( code );
        return nDoucument;
    }
}
