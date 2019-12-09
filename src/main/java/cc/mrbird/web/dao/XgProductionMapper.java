package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgProductionMapper extends MyMapper<XgProduction> {
    int  updateProductionById (XgProduction xgProduction);
    int addProduction(XgProduction xgProduction);
    List<XgProduction> getProductionListByItems(XgProductionPageIn productionPageIn);

    List<XgProduction> getProductionListByItemsWithAll(XgProductionPageIn productionPageIn);
    List<XgProduction> getDashBoardUpProByItems(XgProductionPageIn productionPageIn);


}