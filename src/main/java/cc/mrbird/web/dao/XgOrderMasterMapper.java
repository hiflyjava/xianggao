package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.dto.in.XgOrderPageIn;

import java.util.List;

public interface XgOrderMasterMapper  extends MyMapper<XgOrderMaster> {



    int addOrderMaster(XgOrderMaster record);
    int updateOrderMaster(XgOrderMaster record);

    int updateOrderMasterByOrderNo(XgOrderMaster record);

    List<XgOrderMaster> getMyOrderListByUserId(XgOrderPageIn orderPageIn);

    List<XgOrderMaster> getMyNeedListByUserId(XgOrderPageIn orderPageIn);
    XgOrderMaster getOrderMasterByOrderNum(String orderNum);
}