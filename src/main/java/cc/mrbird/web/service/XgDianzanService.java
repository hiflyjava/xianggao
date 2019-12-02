package cc.mrbird.web.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProductionDianzan;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 10:14
 * @Description:
 */
public interface XgDianzanService extends IService<XgProductionDianzan> {



//    int updateNeedById(XgProductionDianzan dianzan);
//
//    int checkedNeedByAdmin(XgProductionDianzan dianzan);
//
//    PageInfo<XgProductionDianzan> getNeedList(XgProductionDianzan dianzan);


    List<XgProductionDianzan> getProductionDianzanList(XgProductionDianzan xgProductionDianzan);

}
