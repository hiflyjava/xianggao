package cc.mrbird.web.service.impl;

import cc.mrbird.web.dao.*;
import cc.mrbird.web.domain.XgProductionCollection;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.domain.XgProductionShare;
import cc.mrbird.web.domain.XgProductionType;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexProductionOut;
import cc.mrbird.web.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/2 19:52
 * @Description:
 */

@Service
@Transactional
public class DashboardServiceImpl  implements DashboardService {

    @Autowired
    XgProductionShareMapper shareMapper;


    @Autowired
    XgProductionCollectionMapper collectionMapper;

    @Autowired
    XgProductionDianzanMapper dianzanMapper;
    @Autowired
    XgProductionTypeMapper productionTypeMapper;


    @Override
    public IndexProductionOut getDashboardNums(XgProductionPageIn xgProductionPageIn) {

          IndexProductionOut out =new IndexProductionOut();

          List<XgProductionShare> productionShareByItems = shareMapper.getProductionShareByItems(xgProductionPageIn);


          List<XgProductionCollection> productionCollectionByItems = collectionMapper.getProductionCollectionByItems(xgProductionPageIn);
          List<XgProductionDianzan> productionDianzanByItems = dianzanMapper.getProductionDianzanByItems(xgProductionPageIn);
           out.setCollectionNums(productionCollectionByItems.size());
           out.setDianzanNums(productionDianzanByItems.size());
           out.setShareNums(productionShareByItems.size());
        return out;
    }

    @Override
    public List<XgProductionType> getAllProductionType() {
        List<XgProductionType> types = productionTypeMapper.selectAll();
        return types;
    }


}
