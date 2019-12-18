package cc.mrbird.web.service.impl;

import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.domain.User;
import cc.mrbird.web.dao.XgProductionDianzanMapper;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.service.XgProductionDianzanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/18 18:46
 * @Description:
 */

@Service
@Transactional
public class XgProductionDianzanServiceImpl implements XgProductionDianzanService {

    @Autowired
    XgProductionDianzanMapper productionDianzanMapper;


    @Override
    public int addProductionDianzan(Long pid) {
        User user = MyUserUtiles.getUser();
        XgProductionDianzan xgProductionDianzan =new XgProductionDianzan();
        xgProductionDianzan.setCreateDate(new Date());
        xgProductionDianzan.setpId(pid);
        xgProductionDianzan.setUserId(user.getUserId());
        xgProductionDianzan.setUserImg(user.getAvatar());
        xgProductionDianzan.setUsername(user.getUsername());
        return productionDianzanMapper.insertSelective(xgProductionDianzan);
        //return 0;
    }
}
