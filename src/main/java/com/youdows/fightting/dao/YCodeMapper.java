package com.youdows.fightting.dao;

import com.youdows.fightting.entity.YCode;
import com.youdows.fightting.entity.YCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YCodeMapper {
    int countByExample(YCodeExample example);

    int deleteByExample(YCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YCode record);

    int insertSelective(YCode record);

    List<YCode> selectByExample(YCodeExample example);

    YCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YCode record, @Param("example") YCodeExample example);

    int updateByExample(@Param("record") YCode record, @Param("example") YCodeExample example);

    int updateByPrimaryKeySelective(YCode record);

    int updateByPrimaryKey(YCode record);
}