package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgUserLeaveMessage;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgUserLeaveMessageMapper  extends MyMapper<XgUserLeaveMessage> {


    List<XgUserLeaveMessage> getUserLeaveMessageByItems(XgProductionPageIn xgProductionPageIn);

}