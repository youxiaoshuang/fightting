package com.youdows.fightting.service;

import com.youdows.fightting.entity.Tag;

import java.util.List;

/**
 * Created by youxiaoshuang on 2016/11/3.
 * Projiect fightting
 * Author youxiaoshuang
 */
public interface TagService {
    Tag addTag(Tag tag);
    List<Tag> getTags();
}
