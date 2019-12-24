package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgUserFensiMapper extends MyMapper<XgUserFensi> {



    List<XgUserFensi> getUserFensiByItems(XgProductionPageIn xgProductionPageIn);


    int addUserFensi(XgUserFensi record);

    List<XgUserFensi> getUserFensiByUserId(Long userId);

    int updateUserFensi(XgUserFensi record);

}