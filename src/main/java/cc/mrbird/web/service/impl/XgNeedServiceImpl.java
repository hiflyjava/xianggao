package cc.mrbird.web.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.domain.Dept;
import cc.mrbird.system.service.DeptService;
import cc.mrbird.web.dao.XgNeedMapper;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.dto.in.XgNeedPageIn;
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

@Service("xgNeedService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class XgNeedServiceImpl  extends BaseService<XgNeed> implements XgNeedService {


    @Autowired
    XgNeedMapper xgNeedMapper;


    @Override
    public int updateNeedById(XgNeed xgNeed) {
        xgNeed.setUpdateDate(new Date());
        xgNeed.setUpdateBy(MyUserUtiles.getUser().getUsername());
        return xgNeedMapper.updateNeedById(xgNeed);
    }

    @Override
    public int checkedNeedByAdmin(XgNeed xgNeed) {
        xgNeed.setCheckedBy(MyUserUtiles.getUser().getUsername());
        xgNeed.setCheckedDate(new Date());
       return xgNeedMapper.updateNeedById(xgNeed);

    }

    @Override
    public PageInfo<XgNeed> getNeedList(XgNeedPageIn xgNeedPageIn) {
        PageHelper.startPage(xgNeedPageIn.getCurrentPage(), xgNeedPageIn.getPageSize());
        List<XgNeed> needList = xgNeedMapper.getNeedList(xgNeedPageIn);
        return new PageInfo<>(needList);
    }
}
