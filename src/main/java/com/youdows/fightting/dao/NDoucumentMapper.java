package com.youdows.fightting.dao;

import com.youdows.fightting.dto.Page;
import com.youdows.fightting.entity.NDoucument;
import com.youdows.fightting.entity.NDoucumentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NDoucumentMapper {
    int countByExample(NDoucumentExample example);

    int deleteByExample(NDoucumentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NDoucument record);

    int insertSelective(NDoucument record);

    List<NDoucument> selectByExample(NDoucumentExample example);

    List<NDoucument> selectByAllPage(Page page);

    NDoucument selectByPrimaryKey(Integer id);

    NDoucument selectByCode(@Param( "code" ) String code);

    int updateByExampleSelective(@Param("record") NDoucument record, @Param("example") NDoucumentExample example);

    int updateByExample(@Param("record") NDoucument record, @Param("example") NDoucumentExample example);

    int updateByPrimaryKeySelective(NDoucument record);

    int updateByPrimaryKey(NDoucument record);

    int updateByUuid(NDoucument record);
}