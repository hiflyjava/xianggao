package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.dao.XgNeedMapper;
import cc.mrbird.web.dao.XgProductionDianzanMapper;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import cc.mrbird.web.service.XgDianzanService;
import cc.mrbird.web.service.XgNeedService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 10:16
 * @Description:
 */

//@Service("xgDianzanService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgDianzanServiceImpl {

//
//    @Autowired
//    XgProductionDianzanMapper dianzanMapper;
//
//    @Override
//    public List<XgProductionDianzan> getProductionDianzanList(XgProductionDianzan xgProductionDianzan) {
//        return null;
//    }
}
