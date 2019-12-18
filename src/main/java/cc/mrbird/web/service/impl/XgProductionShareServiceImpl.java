package cc.mrbird.web.service.impl;

import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.dao.XgProductionShareMapper;
import cc.mrbird.web.domain.XgProductionShare;
import cc.mrbird.web.service.XgProductionShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/18 18:25
 * @Description:
 */
@Service
@Transactional
public class XgProductionShareServiceImpl  implements XgProductionShareService {


    @Autowired
    XgProductionShareMapper productionShareMapper;


    @Override
    public int addProductionShare(Long pid,String type) {
        XgProductionShare productionShare =new XgProductionShare();
        productionShare.setCreateDate(new Date());
        productionShare.setpId(pid);
        productionShare.setType(type);
        productionShare.setUserId(MyUserUtiles.getUser().getUserId());
        productionShare.setUserImg(MyUserUtiles.getUser().getAvatar());
        productionShare.setUsername(MyUserUtiles.getUser().getUsername());
       return productionShareMapper.insertSelective(productionShare);

    }
}
