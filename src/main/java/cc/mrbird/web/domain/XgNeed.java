package com.pg.bean;

import java.util.Date;

public class XgNeed {
    private Long id;

    private String title;

    private String type;

    private String budget;

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

    private String extend1;

    private String extend2;

    private String extend3;

    private String needInfo;

    private String needCheckStatus;

    private String neddMode;

    private String discount;

    private String hostingState;

    private String needUserType;

    private String needPayAddress;

    private Date createDate;

    private Date updateDate;

    private Integer needDays;

    private String needImgs;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget == null ? null : budget.trim();
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

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1 == null ? null : extend1.trim();
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2 == null ? null : extend2.trim();
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

    public String getNeddMode() {
        return neddMode;
    }

    public void setNeddMode(String neddMode) {
        this.neddMode = neddMode == null ? null : neddMode.trim();
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