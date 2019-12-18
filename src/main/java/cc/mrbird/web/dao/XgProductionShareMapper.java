package cc.mrbird.web.dao;

import cc.mrbird.web.domain.XgProductionShare;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgProductionShareMapper {


    List<XgProductionShare> getProductionShareByItems(XgProductionPageIn xgProductionPageIn);

    int insertSelective(XgProductionShare record);

}