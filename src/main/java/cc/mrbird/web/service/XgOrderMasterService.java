package cc.mrbird.web.service;


import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.dto.in.XgOrderPageIn;
import com.github.pagehelper.PageInfo;

public interface XgOrderMasterService extends IService<XgOrderMaster> {



    int addOrderMaster(XgOrderMaster orderMaster);

    int updateOrderMatder(XgOrderMaster orderMaster);


    PageInfo<XgOrderMaster> getMyOrderListByUserId(XgOrderPageIn orderPageIn);

    PageInfo<XgOrderMaster> getMyNeedListByUserId(XgOrderPageIn orderPageIn);

}
