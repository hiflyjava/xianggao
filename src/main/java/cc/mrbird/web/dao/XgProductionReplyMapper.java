package cc.mrbird.web.dao;

import cc.mrbird.web.domain.XgProductionReply;
import cc.mrbird.web.domain.XgProductionReply;

public interface XgProductionReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XgProductionReply record);

    int insertSelective(XgProductionReply record);

    XgProductionReply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XgProductionReply record);

    int updateByPrimaryKey(XgProductionReply record);
}