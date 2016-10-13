package com.youdows.fightting.dao;

import com.youdows.fightting.entity.YFile;
import com.youdows.fightting.entity.YFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YFileMapper {
    int countByExample(YFileExample example);

    int deleteByExample(YFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YFile record);

    int insertSelective(YFile record);

    List<YFile> selectByExample(YFileExample example);

    YFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YFile record, @Param("example") YFileExample example);

    int updateByExample(@Param("record") YFile record, @Param("example") YFileExample example);

    int updateByPrimaryKeySelective(YFile record);

    int updateByPrimaryKey(YFile record);
}