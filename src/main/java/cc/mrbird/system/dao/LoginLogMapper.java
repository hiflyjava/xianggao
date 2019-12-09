package cc.mrbird.system.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.LoginLog;
import cc.mrbird.system.domain.SysLog;
import cc.mrbird.web.dto.in.LoginLogIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;

import java.util.List;

public interface LoginLogMapper extends MyMapper<LoginLog> {



    List<LoginLog> getLoginListByItems(LoginLogIn loginLogIn);



}