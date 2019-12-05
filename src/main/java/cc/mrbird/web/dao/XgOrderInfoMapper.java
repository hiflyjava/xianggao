package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgOrderInfo;

public interface XgOrderInfoMapper extends MyMapper<XgOrderInfo> {

    int addOrderInfo(XgOrderInfo record);
    int updateOrderInfo(XgOrderInfo record);


}