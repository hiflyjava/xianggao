package cc.mrbird.web.domain;

import java.io.Serializable;
import java.util.Date;

public class XgNeed  implements Serializable {


    private static final long serialVersionUID = -6051593356331868324L;

    private Long id;

    private String title;

    private int type;

    private Long budget;

    private String isHome;

    private Date startTime;

    private Date endTime;

    private String payType;

    private Date firstPayTime;

    private Date secondPayTime;

    private Date thirdlyPayTime;

    private Date fourthlyPayTime;

    private Date fifthPayTime;

    private Long userId;

    private String checkedBy;

    private String updateBy;

    private String extend3;

    private String needInfo;

    private String needCheckStatus;

    private String needMode;

    private String discount;

    private String hostingState;

    private String needUserType;

    private String needPayAddress;

    private Date createDate;

    private Date updateDate;

    private Integer needDays;

    private String needImgs;

    private Date checkedDate;
    private String telephone;

    private String detailCount;//计费

    private String detailMony;//计费金额


    private String username;//账号

    private String signName;//昵称

    private String needType;


    private Long designUserId;//设计师id

    private String needPayStatus;//需求交易状态


    private Date needValidityStartTime;

    private Integer needValidityDays;

    private Date needValidityEndTime;


    public Date getNeedValidityStartTime() {
        return needValidityStartTime;
    }

    public void setNeedValidityStartTime(Date needValidityStartTime) {
        this.needValidityStartTime = needValidityStartTime;
    }

    public Date getNeedValidityEndTime() {
        return needValidityEndTime;
    }

    public void setNeedValidityEndTime(Date needValidityEndTime) {
        this.needValidityEndTime = needValidityEndTime;
    }

    public Integer getNeedValidityDays() {
        return needValidityDays;
    }

    public void setNeedValidityDays(Integer needValidityDays) {
        this.needValidityDays = needValidityDays;
    }

    public String getNeedPayStatus() {
        return needPayStatus;
    }

    public void setNeedPayStatus(String needPayStatus) {
        this.needPayStatus = needPayStatus;
    }

    public Long getDesignUserId() {
        return designUserId;
    }

    public void setDesignUserId(Long designUserId) {
        this.designUserId = designUserId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(String detailCount) {
        this.detailCount = detailCount;
    }

    public String getDetailMony() {
        return detailMony;
    }

    public void setDetailMony(String detailMony) {
        this.detailMony = detailMony;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNeedType() {
        return needType;
    }

    public void setNeedType(String needType) {
        this.needType = needType;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public int getType() {
        return type;
    }


    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public String getIsHome() {
        return isHome;
    }

    public void setIsHome(String isHome) {
        this.isHome = isHome == null ? null : isHome.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getFirstPayTime() {
        return firstPayTime;
    }

    public void setFirstPayTime(Date firstPayTime) {
        this.firstPayTime = firstPayTime;
    }

    public Date getSecondPayTime() {
        return secondPayTime;
    }

    public void setSecondPayTime(Date secondPayTime) {
        this.secondPayTime = secondPayTime;
    }

    public Date getThirdlyPayTime() {
        return thirdlyPayTime;
    }

    public void setThirdlyPayTime(Date thirdlyPayTime) {
        this.thirdlyPayTime = thirdlyPayTime;
    }

    public Date getFourthlyPayTime() {
        return fourthlyPayTime;
    }

    public void setFourthlyPayTime(Date fourthlyPayTime) {
        this.fourthlyPayTime = fourthlyPayTime;
    }

    public Date getFifthPayTime() {
        return fifthPayTime;
    }

    public void setFifthPayTime(Date fifthPayTime) {
        this.fifthPayTime = fifthPayTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

    public String getNeedInfo() {
        return needInfo;
    }

    public void setNeedInfo(String needInfo) {
        this.needInfo = needInfo == null ? null : needInfo.trim();
    }

    public String getNeedCheckStatus() {
        return needCheckStatus;
    }

    public void setNeedCheckStatus(String needCheckStatus) {
        this.needCheckStatus = needCheckStatus == null ? null : needCheckStatus.trim();
    }

    public String getNeedMode() {
        return needMode;
    }

    public void setNeedMode(String needMode) {
        this.needMode = needMode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
    }

    public String getHostingState() {
        return hostingState;
    }

    public void setHostingState(String hostingState) {
        this.hostingState = hostingState == null ? null : hostingState.trim();
    }

    public String getNeedUserType() {
        return needUserType;
    }

    public void setNeedUserType(String needUserType) {
        this.needUserType = needUserType == null ? null : needUserType.trim();
    }

    public String getNeedPayAddress() {
        return needPayAddress;
    }

    public void setNeedPayAddress(String needPayAddress) {
        this.needPayAddress = needPayAddress == null ? null : needPayAddress.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getNeedDays() {
        return needDays;
    }

    public void setNeedDays(Integer needDays) {
        this.needDays = needDays;
    }

    public String getNeedImgs() {
        return needImgs;
    }

    public void setNeedImgs(String needImgs) {
        this.needImgs = needImgs == null ? null : needImgs.trim();
    }
}