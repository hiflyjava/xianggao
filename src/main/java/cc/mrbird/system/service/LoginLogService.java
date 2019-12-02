package cc.mrbird.system.service;

import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.LoginLog;
import cc.mrbird.system.domain.SysLog;
import cc.mrbird.web.dto.in.LoginLogIn;
import cc.mrbird.web.dto.out.LoginLogOut;

import java.util.List;

public interface LoginLogService extends IService<LoginLog> {
	
	List<LoginLog> findAllLogs(LoginLog log);
	
	void deleteLogs(String LoginLog);


	LoginLogOut getLoginListByItems(LoginLogIn loginLogIn);

}
