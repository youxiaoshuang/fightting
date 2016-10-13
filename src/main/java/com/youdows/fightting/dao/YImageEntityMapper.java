package com.youdows.fightting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youdows.fightting.entity.YImageEntity;

public interface YImageEntityMapper {
    int deleteByPrimaryKey(Integer imageid);

    int insert(YImageEntity record);

    int insertSelective(YImageEntity record);

    YImageEntity selectByPrimaryKey(Integer imageid);

    int updateByPrimaryKeySelective(YImageEntity record);

    int updateByPrimaryKey(YImageEntity record);

	List<YImageEntity> selectImagesPage(YImageEntity image);
	
	YImageEntity selectFtpUtlByName(@Param(value="Name")String imageName);
}