package cc.mrbird.system.service.impl;
import	java.util.HashMap;
import	java.util.Map;
import	java.security.KeyStore.Entry.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cc.mrbird.common.util.MyUserUtiles;
import cc.mrbird.system.dao.LoginLogMapper;
import cc.mrbird.system.dao.XgCompanyMapper;

import cc.mrbird.system.domain.XgCompanys;
import cc.mrbird.system.utils.StringUtils;
import cc.mrbird.web.dao.XgUserVipMapper;
import cc.mrbird.web.domain.XgUserVip;
import cc.mrbird.web.dto.in.XgProductionPageIn;
import cc.mrbird.web.dto.out.IndexUserOut;
import cc.mrbird.web.utils.MyDateUtils;
import cc.mrbird.web.utils.XgCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MD5Utils;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.dao.UserRoleMapper;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserRole;
import cc.mrbird.system.domain.UserWithRole;
import cc.mrbird.system.service.UserRoleService;
import cc.mrbird.system.service.UserService;
import tk.mybatis.mapper.entity.Example;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private XgCompanyMapper companyMapper;

//	@Autowired
//	private LoginLogMapper loginLogMapper;

	@Autowired
	private XgUserVipMapper userVipMapper;

	@Override
	public User findByName(String userName) {
		Example example = new Example(User.class);
		example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
		List<User> list = this.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public List<User> findUserWithDept(User user) {
		try {
			Long userId = MyUserUtiles.getUser().getUserId();
			UserRole userRole =new UserRole();
			userRole.setUserId(userId);
			List<UserRole> userRoles = userRoleMapper.select(userRole);
			for(UserRole userRole1:userRoles){
				if(1==userRole1.getRoleId()){
					return this.userMapper.findUserWithDept(user);
				}
			}
			user.setUserId(userId);
			return  this.userMapper.findUserWithDept(user);

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void registUser(User user) {
		user.setCrateTime(new Date());
		user.setTheme(User.DEFAULT_THEME);
		user.setAvatar(User.DEFAULT_AVATAR);
		user.setSsex(User.SEX_UNKNOW);
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		this.save(user);
		UserRole ur = new UserRole();
		ur.setUserId(user.getUserId());
		ur.setRoleId(3l);
		this.userRoleMapper.insert(ur);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateTheme(String theme, String userName) {
		Example example = new Example(User.class);
		example.createCriteria().andCondition("username=", userName);
		User user = new User();
		user.setTheme(theme);
		this.userMapper.updateByExampleSelective(user, example);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user, XgCompanys xgCompany, Long[] roles) {

		Long companyId=0l;

		if(xgCompany !=null){//是企业
			if(StringUtils.notEmptyOrNull(xgCompany.getcName())){
				xgCompany.setCreateDate(new Date());
				xgCompany.setCreateBy(MyUserUtiles.getUser().getUsername());
				int i = companyMapper.insertUseGeneratedKeys(xgCompany);
              companyId=xgCompany.getId();
			}
		}

        user.setCompanyId(companyId);
		user.setCrateTime(new Date());
		user.setTheme(User.DEFAULT_THEME);
		user.setAvatar(User.DEFAULT_AVATAR);
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		this.save(user);
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(user.getUserId());
			ur.setRoleId(roleId);
			this.userRoleMapper.insert(ur);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUser(User user, Long[] roles) {
		user.setPassword(null);
		user.setUsername(null);
		user.setModifyTime(new Date());
		this.updateNotNull(user);
		Example example = new Example(UserRole.class);
		example.createCriteria().andCondition("user_id=", user.getUserId());
		this.userRoleMapper.deleteByExample(example);
		for (Long roleId : roles) {
			UserRole ur = new UserRole();
			ur.setUserId(user.getUserId());
			ur.setRoleId(roleId);
			this.userRoleMapper.insert(ur);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteUsers(String userIds) {
		List<String> list = Arrays.asList(userIds.split(","));
		this.batchDelete(list, "userId", User.class);

		this.userRoleService.deleteUserRolesByUserId(userIds);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateLoginTime(String userName) {
		Example example = new Example(User.class);
		example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
		User user = new User();
		user.setLastLoginTime(new Date());
		this.userMapper.updateByExampleSelective(user, example);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updatePassword(String password) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Example example = new Example(User.class);
		example.createCriteria().andCondition("username=", user.getUsername());
		String newPassword = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
		user.setPassword(newPassword);
		this.userMapper.updateByExampleSelective(user, example);
	}

	@Override
	public UserWithRole findById(Long userId) {
		List<UserWithRole> list = this.userMapper.findUserWithRole(userId);
		List<Long> roleList = new ArrayList<Long>();
		for (UserWithRole uwr : list) {
			roleList.add(uwr.getRoleId());
		}
		if (list.size() == 0) {
			return null;
		}
		UserWithRole userWithRole = list.get(0);
		userWithRole.setRoleIds(roleList);
		return userWithRole;
	}

	@Override
	public User findUserProfile(User user) {
		return this.userMapper.findUserProfile(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateUserProfile(User user) {
		user.setUsername(null);
		user.setPassword(null);
		if (user.getDeptId() == null)
			user.setDeptId(0l);
		this.updateNotNull(user);
	}

	@Override
	public Map<String, IndexUserOut> getDashBoardUsersByItems(XgProductionPageIn productionPageIn) {

		Map<String, IndexUserOut> map =new HashMap<>();

		IndexUserOut indexUserOut =new IndexUserOut();
		//查询 所有新增用户 st=1;
		//productionPageIn.setSelectType(1);
		//得到当天的
		String startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> todayAddUsers = userMapper.getDashBoardUsersByItems(productionPageIn);//新增的用户
		if(todayAddUsers ==null){
			indexUserOut.setTodayNum(0);
		}else {
			indexUserOut.setTodayNum(todayAddUsers.size());
		}

		//得到过去一周的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> weekAddUsers = userMapper.getDashBoardUsersByItems(productionPageIn);//新增的用户
		if(weekAddUsers ==null){
			indexUserOut.setWeekNum(0);
		}else {
			indexUserOut.setWeekNum(weekAddUsers.size());
		}

		//得到过去一个月的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> mouthAddUsers = userMapper.getDashBoardUsersByItems(productionPageIn);//新增的用户
		if(mouthAddUsers ==null){
			indexUserOut.setMouthNum(0);
		}else {
			indexUserOut.setMouthNum(mouthAddUsers.size());
		}


		//得到过去半年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> helfYearAddUsers = userMapper.getDashBoardUsersByItems(productionPageIn);//新增的用户
		if(helfYearAddUsers ==null){
			indexUserOut.setHelfYear(0);
		}else {
			indexUserOut.setHelfYear(helfYearAddUsers.size());
		}


		//得到过去一年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> yearAddUsers = userMapper.getDashBoardUsersByItems(productionPageIn);//新增的用户
		if(yearAddUsers ==null){
			indexUserOut.setOneYear(0);
		}else {
			indexUserOut.setOneYear(yearAddUsers.size());
		}

		map.put("AddUsers",indexUserOut);




		IndexUserOut indexUserOut2 =new IndexUserOut();
		//查询 所有新增用户 st=1;
		//productionPageIn.setSelectType(1);
		//得到当天的
		 startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> notLoginUserToday = userMapper.getNotLoginUserByItems(productionPageIn);//没有登录的用户
		if(notLoginUserToday ==null){
			indexUserOut2.setTodayNum(0);
		}else {
			indexUserOut2.setTodayNum(notLoginUserToday.size());
		}

		//得到过去一周的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> notLoginUserWeek = userMapper.getNotLoginUserByItems(productionPageIn);//没有登录的用户
		if(notLoginUserWeek ==null){
			indexUserOut2.setWeekNum(0);
		}else {
			indexUserOut2.setWeekNum(notLoginUserWeek.size());
		}

		//得到过去一个月的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> notLoginUserMouth = userMapper.getNotLoginUserByItems(productionPageIn);//没有登录的用户
		if(notLoginUserMouth ==null){
			indexUserOut2.setMouthNum(0);
		}else {
			indexUserOut2.setMouthNum(notLoginUserMouth.size());
		}


		//得到过去半年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> notLoginUserHelfYear = userMapper.getNotLoginUserByItems(productionPageIn);//没有登录的用户
		if(notLoginUserHelfYear ==null){
			indexUserOut2.setHelfYear(0);
		}else {
			indexUserOut2.setHelfYear(notLoginUserHelfYear.size());
		}


		//得到过去一年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<User> notLoginUserYear = userMapper.getNotLoginUserByItems(productionPageIn);//没有登录的用户
		if(notLoginUserYear ==null){
			indexUserOut2.setOneYear(0);
		}else {
			indexUserOut2.setOneYear(notLoginUserYear.size());
		}

		map.put("NoLoginUsers",indexUserOut2);





		IndexUserOut indexUserOut3 =new IndexUserOut();
		//查询 所有新增用户 st=1;
		//productionPageIn.setSelectType(1);
		//得到当天的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userVipAddToday = userVipMapper.getUserVipByItems(productionPageIn);//新增vip用户
		if(userVipAddToday ==null){
			indexUserOut3.setTodayNum(0);
		}else {
			indexUserOut3.setTodayNum(userVipAddToday.size());
		}

		//得到过去一周的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userVipAddWeek = userVipMapper.getUserVipByItems(productionPageIn);//新增vip用户
		if(userVipAddWeek ==null){
			indexUserOut3.setWeekNum(0);
		}else {
			indexUserOut3.setWeekNum(userVipAddWeek.size());
		}

		//得到过去一个月的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userVipAddMouth = userVipMapper.getUserVipByItems(productionPageIn);//新增vip用户
		if(userVipAddMouth ==null){
			indexUserOut3.setMouthNum(0);
		}else {
			indexUserOut3.setMouthNum(userVipAddMouth.size());
		}


		//得到过去半年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userVipAddHelfYear = userVipMapper.getUserVipByItems(productionPageIn);//新增vip用户
		if(userVipAddHelfYear ==null){
			indexUserOut3.setHelfYear(0);
		}else {
			indexUserOut3.setHelfYear(userVipAddHelfYear.size());
		}


		//得到过去一年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userVipAddYear = userVipMapper.getUserVipByItems(productionPageIn);//新增vip用户
		if(userVipAddYear ==null){
			indexUserOut3.setOneYear(0);
		}else {
			indexUserOut3.setOneYear(userVipAddYear.size());
		}

		map.put("AddVipUsers",indexUserOut3);



		IndexUserOut indexUserOut4 =new IndexUserOut();
		//查询 所有新增用户 st=1;
		//productionPageIn.setSelectType(1);
		//得到当天的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.TODAT, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userOutVipToday = userVipMapper.getUserOutVipByItems(productionPageIn);//过期vip用户
		if(userOutVipToday ==null){
			indexUserOut4.setTodayNum(0);
		}else {
			indexUserOut4.setTodayNum(userOutVipToday.size());
		}

		//得到过去一周的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_WEEK, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userOutVipWeek = userVipMapper.getUserOutVipByItems(productionPageIn);//过期vip用户
		if(userOutVipWeek ==null){
			indexUserOut4.setWeekNum(0);
		}else {
			indexUserOut4.setWeekNum(userOutVipWeek.size());
		}

		//得到过去一个月的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_MOUTH, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userOutVipMouth = userVipMapper.getUserOutVipByItems(productionPageIn);//过期vip用户
		if(userOutVipMouth ==null){
			indexUserOut4.setMouthNum(0);
		}else {
			indexUserOut4.setMouthNum(userOutVipMouth.size());
		}


		//得到过去半年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_HELF_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userOutVipHelfYear = userVipMapper.getUserOutVipByItems(productionPageIn);//过期vip用户
		if(userOutVipHelfYear ==null){
			indexUserOut4.setHelfYear(0);
		}else {
			indexUserOut4.setHelfYear(userOutVipHelfYear.size());
		}


		//得到过去一年的
		startTime = MyDateUtils.getDifferDateByInt(XgCodeUtil.ONE_YEAR, productionPageIn.getEndTime());
		productionPageIn.setStartTime(startTime);
		List<XgUserVip> userOutVipYear = userVipMapper.getUserOutVipByItems(productionPageIn);//过期vip用户
		if(userOutVipYear ==null){
			indexUserOut4.setOneYear(0);
		}else {
			indexUserOut4.setOneYear(userOutVipYear.size());
		}

		map.put("OutVipUsers",indexUserOut4);

		//List<User> notLoginUserByItems = userMapper.getNotLoginUserByItems(productionPageIn);//没有登录的用户
		//List<XgUserVip> userVipByItems = userVipMapper.getUserVipByItems(productionPageIn);//新增vip用户
		//List<XgUserVip> userOutVipByItems = userVipMapper.getUserOutVipByItems(productionPageIn);//过期vip用户


		return map;
	}

}
