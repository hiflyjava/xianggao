package cc.mrbird.system.service.impl;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.system.dao.LoginLogMapper;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.domain.LoginLog;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.service.LogService;
import cc.mrbird.system.service.LoginLogService;
import cc.mrbird.web.dto.in.LoginLogIn;
import cc.mrbird.web.dto.out.LoginLogOut;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("loginLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends BaseService<LoginLog> implements LoginLogService {


	@Autowired
	LoginLogMapper loginLogMapper;

	@Autowired
	UserMapper userMapper;


	@Override
	public List<LoginLog> findAllLogs(LoginLog log) {
		try {
			Example example = new Example(LoginLog.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(log.getUsername())) {
				criteria.andCondition("username=", log.getUsername().toLowerCase());
			}
			if (StringUtils.isNotBlank(log.getOperation())) {
				criteria.andCondition("operation like", "%" + log.getOperation() + "%");
			}
			if (StringUtils.isNotBlank(log.getTimeField())) {
				String[] timeArr = log.getTimeField().split("~");
				criteria.andCondition("date_format(CREATE_TIME,'%Y-%m-%d') >=", timeArr[0]);
				criteria.andCondition("date_format(CREATE_TIME,'%Y-%m-%d') <=", timeArr[1]);
			}
			example.setOrderByClause("create_time");
			return this.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<LoginLog>();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteLogs(String logIds) {
		List<String> list = Arrays.asList(logIds.split(","));
		this.batchDelete(list, "id", LoginLog.class);
	}

	@Override
	public LoginLogOut getLoginListByItems(LoginLogIn loginLogIn) {
        LoginLogOut loginLogOut =new LoginLogOut();



		List<LoginLog> loginList = loginLogMapper.getLoginListByItems(loginLogIn);
		List<User> users = userMapper.selectAll();
		if(users !=null && users.size()>=1){
			loginLogOut.setAllUserNums(users.size());
		}else {
			loginLogOut.setAllUserNums(0);
		}

		if(loginList !=null && loginList.size()>=1){
			loginLogOut.setOneDayLoginNums(loginList.size());
		}else {
			loginLogOut.setOneDayLoginNums(0);
		}

		return loginLogOut;
	}

}
