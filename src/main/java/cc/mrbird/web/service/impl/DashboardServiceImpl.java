package cc.mrbird.web.service.impl;

import cc.mrbird.web.dao.*;
import cc.mrbird.web.domain.*;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexProductionOuts;
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


    @Autowired
    XgUserFensiMapper fensiMapper;

    @Autowired
    XgUserLeaveMessageMapper leaveMessageMapper;

    @Autowired
    XgProductionReplyMapper replyMapper;


    @Override
    public IndexProductionOuts getDashboardNums(XgProductionPageIn xgProductionPageIn) {

          IndexProductionOuts out =new IndexProductionOuts();

          List<XgProductionShare> productionShareByItems = shareMapper.getProductionShareByItems(xgProductionPageIn);

          List<XgProductionCollection> productionCollectionByItems = collectionMapper.getProductionCollectionByItems(xgProductionPageIn);
          List<XgProductionDianzan> productionDianzanByItems = dianzanMapper.getProductionDianzanByItems(xgProductionPageIn);
        List<XgUserFensi> userFensiByItems = fensiMapper.getUserFensiByItems(xgProductionPageIn);
        List<XgUserLeaveMessage> userLeaveMessageByItems = leaveMessageMapper.getUserLeaveMessageByItems(xgProductionPageIn);
        List<XgProductionReply> productionReplyByItems = replyMapper.getProductionReplyByItems(xgProductionPageIn);

           out.setCollectionNums(productionCollectionByItems.size());
           out.setDianzanNums(productionDianzanByItems.size());
           out.setShareNums(productionShareByItems.size());
           out.setFensi(userFensiByItems.size());
           out.setLeaveMessageNums(userLeaveMessageByItems.size());
           out.setReplyNums(productionReplyByItems.size());
        return out;
    }

    @Override
    public List<XgProductionType> getAllProductionType() {
        List<XgProductionType> types = productionTypeMapper.selectAll();
        return types;
    }


}
