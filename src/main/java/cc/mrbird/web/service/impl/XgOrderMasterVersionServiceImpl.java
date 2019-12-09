package cc.mrbird.web.service.impl;
import	java.awt.Desktop.Action;
import java.util.Date;
import java.util.List;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.web.dao.XgOrderMasterVersionMapper;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgOrderMasterVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("xgOrderMasterVersionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgOrderMasterVersionServiceImpl extends BaseService<XgOrderMasterVersion> implements XgOrderMasterVersionService {

    @Autowired
    XgOrderMasterVersionMapper orderMasterVersionMapper;


    @Override
    public PageInfo<XgOrderMasterVersion> getOrderListByItems(XgProductionPageIn productionPageIn) {

        PageHelper.startPage(productionPageIn.getCurrentPage(),productionPageIn.getPageSize());
        List<XgOrderMasterVersion> list = orderMasterVersionMapper.getOrderListByItems(productionPageIn);
        return new PageInfo<> (list);
    }

    @Override
    public int addOrderMasterVersion(XgOrderMasterVersion xgOrderMasterVersion) {

        return 0;
    }
}
