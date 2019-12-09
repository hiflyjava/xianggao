package cc.mrbird.web.dao;
import	java.util.List;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgUserVip;
import cc.mrbird.web.dto.in.XgProductionPageIn;

public interface XgUserVipMapper extends MyMapper<XgUserVip> {




    int addXgUserVip(XgUserVip record);

    int updateXgUserVip(XgUserVip record);

    List <XgUserVip> getUserVipByItems(XgProductionPageIn xgProductionPageIn);


    List <XgUserVip>  getUserOutVipByItems (XgProductionPageIn xgProductionPageIn);

}