package cc.mrbird.web.dao;


import cc.mrbird.web.domain.XgProductionCollection;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgProductionCollectionMapper {

    List<XgProductionCollection> getProductionCollectionByItems(XgProductionPageIn xgProductionPageIn);

}