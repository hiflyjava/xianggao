package cc.mrbird.system.dao;

import java.util.List;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.LoginLog;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserWithRole;
import cc.mrbird.web.dto.in.UserPageIn;
import cc.mrbird.web.dto.in.XgProductionPageIn;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);

	List<User> findMyWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);


	List <User> getDashBoardUsersByItems(XgProductionPageIn xgProductionPageIn);

	List <User> getNotLoginUserByItems(XgProductionPageIn xgProductionPageIn);

	User getUserInfoById(Long userId);

	List<User>  getDesignUserListByItems(UserPageIn userPageIn);

	List<User> getMyStarDesignUserListByItems(UserPageIn userPageIn);

	List<User> getMyFansDesignUserListByItems (UserPageIn userPageIn);
}