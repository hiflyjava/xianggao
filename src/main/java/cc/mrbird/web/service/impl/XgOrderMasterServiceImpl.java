package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.XgOrderMasterMapper;
import cc.mrbird.web.dao.XgOrderMasterVersionMapper;
import cc.mrbird.web.domain.XgOrderMaster;
import cc.mrbird.web.domain.XgOrderMasterVersion;
import cc.mrbird.web.dto.in.XgOrderPageIn;
import cc.mrbird.web.service.XgOrderMasterService;
import cc.mrbird.web.utils.XgCodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


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

    @Override
    public int updateOrderMatder(XgOrderMaster orderMaster) {

        orderMaster.setUpdateDate(new Date());

        orderMasterMapper.updateOrderMasterByOrderNo(orderMaster);
        XgOrderMasterVersion orderMasterVersion =new XgOrderMasterVersion();
        XgOrderMaster masterByOrderNum = orderMasterMapper.getOrderMasterByOrderNum(orderMaster.getOrderNum());
        if(masterByOrderNum ==null){
            return  -1;
        }
        BeanUtils.copyProperties(masterByOrderNum,orderMasterVersion);
        orderMasterVersion.setCreateDate(new Date());
        orderMasterVersion.setUpdateDate(new Date());
        orderMasterVersion.setPayStatus(orderMaster.getPayStatus());
        orderMasterVersion.setId(null);
        int i = orderMasterVersionMapper.addOrderMasterVersion(orderMasterVersion);
        return i;
    }

    @Override
    public PageInfo<XgOrderMaster> getMyOrderListByUserId(XgOrderPageIn orderPageIn) {
        PageHelper.startPage(orderPageIn.getCurrentPage(), orderPageIn.getPageSize());
        orderPageIn.setDesignUserId(MyUserUtiles.getUser().getUserId());
        List<XgOrderMaster> orderListByUserId = orderMasterMapper.getMyOrderListByUserId(orderPageIn);
        return new PageInfo<>(orderListByUserId);
    }

    @Override
    public PageInfo<XgOrderMaster> getMyNeedListByUserId(XgOrderPageIn orderPageIn) {
        PageHelper.startPage(orderPageIn.getCurrentPage(), orderPageIn.getPageSize());
        orderPageIn.setNeedUserId(MyUserUtiles.getUser().getUserId());
        List<XgOrderMaster> needListByUserId = orderMasterMapper.getMyNeedListByUserId(orderPageIn);
        return new PageInfo<>(needListByUserId);
    }
}
