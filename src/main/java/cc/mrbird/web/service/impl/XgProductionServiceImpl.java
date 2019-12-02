package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.web.dao.XgProductionMapper;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.service.XgNeedService;
import cc.mrbird.web.service.XgProductionService;
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
 * @Date: 2019/11/23 12:24
 * @Description:
 */

@Service("xgProductionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgProductionServiceImpl extends BaseService<XgProduction> implements XgProductionService {

    @Autowired
    XgProductionMapper productionMapper;


    @Override
    public int updateProductionById(XgProduction xgProduction) {
        xgProduction.setUpdateDate(new Date());
        xgProduction.setUpdateBy(MyUserUtiles.getUser().getUsername());
        return productionMapper.updateProductionById(xgProduction);
    }

    @Override
    public int checkedProductionById(XgProduction xgProduction) {
        xgProduction.setCheckedBy(MyUserUtiles.getUser().getUsername());
        xgProduction.setCheckedDate(new Date());
        return productionMapper.updateProductionById(xgProduction);
    }

    @Override
    public int addProduction(XgProduction xgProduction) {
        return productionMapper.addProduction(xgProduction);
    }

    @Override
    public PageInfo<XgProduction> getProductionListByItems(XgProductionPageIn productionPageIn) {
        PageHelper.startPage(productionPageIn.getCurrentPage(), productionPageIn.getPageSize());
        List<XgProduction> list = productionMapper.getProductionListByItems(productionPageIn);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<XgProduction> getProductionListByItemsWithAll(XgProductionPageIn productionPageIn) {
        PageHelper.startPage(productionPageIn.getCurrentPage(), productionPageIn.getPageSize());
         productionPageIn.setUserId(MyUserUtiles.getUser().getUserId());
        List<XgProduction> list = productionMapper.getProductionListByItemsWithAll(productionPageIn);
        return new PageInfo<>(list);
    }
}
