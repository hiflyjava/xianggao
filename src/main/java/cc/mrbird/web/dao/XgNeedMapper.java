package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.dto.in.XgNeedPageIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 10:18
 * @Description:
 */
public interface XgNeedMapper extends MyMapper<XgNeed> {

    int updateNeedById(XgNeed xgNeed);

    List<XgNeed> getNeedList(XgNeedPageIn needPageIn);


    List<XgNeed> getDashBoardUpNeedByItems(XgProductionPageIn productionPageIn);


}
