package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.XgOrderMasterMapper;
import cc.mrbird.web.dao.XgOrderMasterVersionMapper;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.service.XgOrderMasterService;
import cc.mrbird.web.utils.XgCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service("xgOrderMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgOrderMasterServiceImpl extends BaseService<XgOrderMaster> implements XgOrderMasterService {

    @Autowired
    XgOrderMasterMapper orderMasterMapper;


    @Autowired
    XgOrderMasterVersionMapper orderMasterVersionMapper;



    @Override
    public int addOrderMaster(XgOrderMaster orderMaster) {
        User user = MyUserUtiles.getUser();
        orderMaster.setUserId(user.getUserId());
        orderMaster.setCreateDate(new Date());
        orderMaster.setUserImg(user.getAvatar());
        orderMaster.setUserName(user.getUsername());
        orderMaster.setPayStatus(XgCodeUtil.ORDER_PAY_STATUS_NO);//设置为未支付

        orderMasterMapper.addOrderMaster(orderMaster);

        XgOrderMasterVersion orderMasterVersion =new XgOrderMasterVersion();
        BeanUtils.copyProperties(orderMaster, orderMasterVersion);
        int flag = orderMasterVersionMapper.addOrderMasterVersion(orderMasterVersion);
        return  flag;
    }
}
