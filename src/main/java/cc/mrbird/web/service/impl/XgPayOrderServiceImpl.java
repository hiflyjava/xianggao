package cc.mrbird.web.service.impl;
import	java.awt.Desktop.Action;
import java.util.Date;


import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.XgPayOrderMapper;
import cc.mrbird.web.domain.XgPayOrder;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.service.XgPayOrderService;
import cc.mrbird.web.service.XgProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("xgPayOrderService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)

public class XgPayOrderServiceImpl  extends BaseService<XgPayOrder> implements XgPayOrderService {

    @Autowired
    private XgPayOrderMapper payOrderMapper;

    @Override
    public XgPayOrder getPayOrderByOrderNo(String orderNo) {
        return payOrderMapper.getPayOrderByOrderNo(orderNo);
    }

    @Override
    public int updatePayOrder(XgPayOrder payOrder) {
        payOrder.setUpdateTime(new Date());
        return payOrderMapper.updatePayOrder(payOrder);
    }
}
