package com.pg.mapper;

import com.pg.bean.XgProduction;

public interface XgProductionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XgProduction record);

    int insertSelective(XgProduction record);

    XgProduction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XgProduction record);

    int updateByPrimaryKey(XgProduction record);
}