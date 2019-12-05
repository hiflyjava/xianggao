package cc.mrbird.web.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.web.domain.XgOrderMasterVersion;

public interface XgOrderMasterVersionMapper extends MyMapper<XgOrderMasterVersion> {

    int addOrderMasterVersion(XgOrderMasterVersion record);


}