package cc.mrbird.web.dao;

import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.domain.XgUserFensi;

public interface XgUserFensiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XgUserFensi record);

    int insertSelective(XgUserFensi record);

    XgUserFensi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XgUserFensi record);

    int updateByPrimaryKey(XgUserFensi record);
}