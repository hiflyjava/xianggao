package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface XgOrderMasterVersionMapper extends MyMapper<XgOrderMasterVersion> {

    int addOrderMasterVersion(XgOrderMasterVersion record);



    List<XgOrderMasterVersion> getOrderListByItems(XgProductionPageIn xgProductionPageIn);
}