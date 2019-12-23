package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.domain.XgPayOrder;

public interface XgPayOrderMapper  extends MyMapper<XgPayOrder> {



    int updatePayOrder(XgPayOrder record);

    XgPayOrder getPayOrderByOrderNo(String orderNo);

    int addPayOrder(XgPayOrder record);

}