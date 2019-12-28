package cc.mrbird.system.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.mrbird.common.annotation.ExportConfig;
import cc.mrbird.web.domain.XgNeed;
import cc.mrbird.web.domain.XgProduction;
import cc.mrbird.web.domain.XgUserFensi;

@Table(name = "t_user")
public class User implements Serializable {

	private static final long serialVersionUID = -4852732617765810959L;
	/**
	 * 账户有效
	 */
	public static final String STATUS_VALID = "1";
	/**
	 * 账户锁定
	 */
	public static final String STATUS_LOCK = "0";

	public static final String SEX_UNKNOW = "2";

	public static final String DEFAULT_THEME = "green";

	public static final String DEFAULT_AVATAR = "default.jpg";

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USERNAME")
	@ExportConfig(value = "用户名")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "DEPT_ID")
	private Long deptId;

	@Transient
	@ExportConfig(value = "部门")
	private String deptName;

	@Column(name = "EMAIL")
	@ExportConfig(value = "邮箱")
	private String email;

	@Column(name = "MOBILE")
	@ExportConfig(value = "手机")
	private String mobile;

	@Column(name = "STATUS")
	@ExportConfig(value = "状态", convert = "s:0=锁定,1=有效")
	private String status = STATUS_VALID;

	@Column(name = "CRATE_TIME")
	@ExportConfig(value = "创建时间", convert = "c:cc.mrbird.common.util.poi.convert.TimeConvert")
	private Date crateTime;

	@Column(name = "MODIFY_TIME")
	private Date modifyTime;

	@Column(name = "LAST_LOGIN_TIME")
	private Date lastLoginTime;

	@Column(name = "SSEX")
	@ExportConfig(value = "性别", convert = "s:0=男,1=女,2=保密")
	private String ssex;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "AVATAR")
	private String avatar;

	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "smsnum")
	private Long smsnum;

	@Transient
	private String roleName;

	

	private String isVip; //Y:是 N：不是

	private String profession;// 职业

	private String school;

	private String registedAddress;

	private String extend1;

	private String workType;

	private String experienceLevel;

	private Long companyId;

	private XgCompanys companys;//0 是个人；其它是公司


	private  Date createVipDate;

	private Date downVipDate;

	private Date downDate;


	private String rightName;

	private String idCard;

	private String cardOnImg;

	private String cardOffImg;

	private Date cardOffTime;

	private String familyAddress;

	private Long monyHour;

	private Long monyMouth;

    @Transient
	private String theme;


	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Long getMonyHour() {
		return monyHour;
	}

	public void setMonyHour(Long monyHour) {
		this.monyHour = monyHour;
	}

	public Long getMonyMouth() {
		return monyMouth;
	}

	public void setMonyMouth(Long monyMouth) {
		this.monyMouth = monyMouth;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCardOnImg() {
		return cardOnImg;
	}

	public void setCardOnImg(String cardOnImg) {
		this.cardOnImg = cardOnImg;
	}

	public String getCardOffImg() {
		return cardOffImg;
	}

	public void setCardOffImg(String cardOffImg) {
		this.cardOffImg = cardOffImg;
	}

	public Date getCardOffTime() {
		return cardOffTime;
	}

	public void setCardOffTime(Date cardOffTime) {
		this.cardOffTime = cardOffTime;
	}

	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	@Transient
	private String startTime; //开始时间入参
    @Transient
	private String endTime;// 结束时间入参


	private List<XgUserFensi> fensis;//粉丝数

	private List<XgProduction> productions;//作品数


	private int fensiCount;

	private int productionCount;

	private String isFansEachOther;//是否互相关注; YES NO

	public String getIsFansEachOther() {
		return isFansEachOther;
	}

	public void setIsFansEachOther(String isFansEachOther) {
		this.isFansEachOther = isFansEachOther;
	}

	public int getFensiCount() {
		return fensiCount;
	}

	public void setFensiCount(int fensiCount) {
		this.fensiCount = fensiCount;
	}

	public int getProductionCount() {
		return productionCount;
	}

	public void setProductionCount(int productionCount) {
		this.productionCount = productionCount;
	}

	public List<XgUserFensi> getFensis() {
		return fensis;
	}

	public void setFensis(List<XgUserFensi> fensis) {
		this.fensis = fensis;
	}

	public List<XgProduction> getProductions() {
		return productions;
	}

	public void setProductions(List<XgProduction> productions) {
		this.productions = productions;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getDownDate() {
		return downDate;
	}

	public void setDownDate(Date downDate) {
		this.downDate = downDate;
	}

	public Date getCreateVipDate() {
		return createVipDate;
	}

	public void setCreateVipDate(Date createVipDate) {
		this.createVipDate = createVipDate;
	}

	public Date getDownVipDate() {
		return downVipDate;
	}

	public void setDownVipDate(Date downVipDate) {
		this.downVipDate = downVipDate;
	}

	//	private List<XgProduction> xgProductions;//作品
//
//	private  List<XgNeed> xgNeeds;
//



	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public XgCompanys getCompanys() {
		return companys;
	}

	public void setCompanys(XgCompanys companys) {
		this.companys = companys;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getRegistedAddress() {
		return registedAddress;
	}

	public void setRegistedAddress(String registedAddress) {
		this.registedAddress = registedAddress;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}


	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	/**
	 * @return USER_ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return USERNAME
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * @return PASSWORD
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * @return DEPT_ID
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return EMAIL
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * @return MOBILE
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * @return STATUS
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * @return CRATE_TIME
	 */
	public Date getCrateTime() {
		return crateTime;
	}

	/**
	 * @param crateTime
	 */
	public void setCrateTime(Date crateTime) {
		this.crateTime = crateTime;
	}

	/**
	 * @return MODIFY_TIME
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * @return LAST_LOGIN_TIME
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return SSEX
	 */
	public String getSsex() {
		return ssex;
	}

	/**
	 * @param ssex
	 */
	public void setSsex(String ssex) {
		this.ssex = ssex == null ? null : ssex.trim();
	}


	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getSmsnum() {
		return smsnum;
	}

	public void setSmsnum(Long smsnum) {
		this.smsnum = smsnum;
	}
}