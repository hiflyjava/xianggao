package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgOrderMaster;

public interface XgOrderMasterMapper  extends MyMapper<XgOrderMaster> {



    int addOrderMaster(XgOrderMaster record);
    int updateOrderMaster(XgOrderMaster record);


}