package cc.mrbird.web.dao;


import cc.mrbird.web.domain.XgProductionCollection;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgProductionDianzanMapper {

    List<XgProductionDianzan> getProductionDianzanByItems(XgProductionPageIn xgProductionPageIn);

}