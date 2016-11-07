package com.youdows.fightting.service.impl;

import com.youdows.fightting.dao.TagMapper;
import com.youdows.fightting.entity.Tag;
import com.youdows.fightting.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by youxiaoshuang on 2016/11/3.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    public Tag addTag(Tag tag) {
        int i = tagMapper.insertSelective( tag );
        tag.setId( i );
        return tag;
    }

    public List<Tag> getTags() {
        return tagMapper.selectAll();
    }
}
