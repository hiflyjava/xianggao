package cc.mrbird.web.service.impl;

import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.dao.XgProductionCollectionMapper;
import cc.mrbird.web.domain.XgProductionCollection;
import cc.mrbird.web.service.XgProductionCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/18 18:40
 * @Description:
 */

@Service
@Transactional
public class XgProductionCollectionServiceImpl implements XgProductionCollectionService {

@Autowired
    XgProductionCollectionMapper productionCollectionMapper;

    @Override
    public int collectionProduction(Long pid) {

        XgProductionCollection xgProductionCollection =new XgProductionCollection();
        xgProductionCollection.setCreateDate(new Date());
        xgProductionCollection.setpId(pid);
        xgProductionCollection.setUserId(MyUserUtiles.getUser().getUserId());
        xgProductionCollection.setUserImg(MyUserUtiles.getUser().getAvatar());
        xgProductionCollection.setUsername(MyUserUtiles.getUser().getUsername());
       return productionCollectionMapper.insertSelective(xgProductionCollection);

    }
}
