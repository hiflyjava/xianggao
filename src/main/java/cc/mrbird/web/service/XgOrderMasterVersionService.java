package cc.mrbird.web.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import com.github.pagehelper.PageInfo;

public interface XgOrderMasterVersionService extends IService<XgOrderMasterVersion> {


    PageInfo<XgOrderMasterVersion> getOrderListByItems(XgProductionPageIn productionPageIn);

    int addOrderMasterVersion(XgOrderMasterVersion xgOrderMasterVersion);

}
