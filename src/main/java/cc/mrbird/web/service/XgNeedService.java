package cc.mrbird.web.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Dept;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 10:14
 * @Description:
 */
public interface XgNeedService extends IService<XgNeed> {



    int updateNeedById(XgNeed xgNeed);

    int checkedNeedByAdmin(XgNeed xgNeed);

    PageInfo<XgNeed> getNeedList(XgNeedPageIn xgNeedPageIn);

}
