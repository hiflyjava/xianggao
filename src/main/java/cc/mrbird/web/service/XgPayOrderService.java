package cc.mrbird.web.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.domain.XgPayOrder;

public interface XgPayOrderService  extends IService<XgPayOrder> {

    XgPayOrder getPayOrderByOrderNo(String orderNo);
    int updatePayOrder(XgPayOrder payOrder);

    int addPayOrder(XgPayOrder payOrder);
}
