package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgProductionReply;
import cc.mrbird.web.domain.XgProductionReply;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface XgProductionReplyMapper extends MyMapper<XgProductionReply> {



    List<XgProductionReply> getProductionReplyByItems(XgProductionPageIn xgProductionPageIn);


}