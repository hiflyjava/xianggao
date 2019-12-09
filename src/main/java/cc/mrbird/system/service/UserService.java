package cc.mrbird.system.service;

import java.util.List;
import java.util.Map;

import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserWithRole;
import cc.mrbird.system.domain.XgCompanys;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexUserOut;
import io.swagger.models.auth.In;

public interface UserService extends IService<User> {

	UserWithRole findById(Long userId);
	
	User findByName(String userName);

	List<User> findUserWithDept(User user);

	void registUser(User user);

	void updateTheme(String theme, String userName);

	void addUser(User user,XgCompanys xgCompany,  Long[] roles);

	void updateUser(User user, Long[] roles);
	
	void deleteUsers(String userIds);

	void updateLoginTime(String userName);
	
	void updatePassword(String password);
	
	User findUserProfile(User user);
	
	void updateUserProfile(User user);

	Map<String , IndexUserOut> getDashBoardUsersByItems(XgProductionPageIn productionPageIn);


}
