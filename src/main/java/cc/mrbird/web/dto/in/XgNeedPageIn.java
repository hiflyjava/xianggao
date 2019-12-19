package cc.mrbird.web.dto.in;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 12:02
 * @Description:
 */
public class XgNeedPageIn implements Serializable {


    private  int pageSize=20;

    private int currentPage=1;



    private String username;

    private String startTime;

    private String endTime;

    private Integer type;

    private Integer validityDays; //几天内到期

    private  String nowTime; //今天时间
    private  String needUserType; //ONE COMPANY
    private  String needPayAddress; //
    private  String needPayStatus; //需求支付状态
    private  String hostingState;  //是否驻场
    private  Long startMony;   //开始金额
    private  Long endMony;     //结束金额
    private  String needMode;  //需求模式


    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getNeedUserType() {
        return needUserType;
    }

    public void setNeedUserType(String needUserType) {
        this.needUserType = needUserType;
    }

    public String getNeedPayAddress() {
        return needPayAddress;
    }

    public void setNeedPayAddress(String needPayAddress) {
        this.needPayAddress = needPayAddress;
    }

    public String getNeedPayStatus() {
        return needPayStatus;
    }

    public void setNeedPayStatus(String needPayStatus) {
        this.needPayStatus = needPayStatus;
    }

    public String getHostingState() {
        return hostingState;
    }

    public void setHostingState(String hostingState) {
        this.hostingState = hostingState;
    }

    public Long getStartMony() {
        return startMony;
    }

    public void setStartMony(Long startMony) {
        this.startMony = startMony;
    }

    public Long getEndMony() {
        return endMony;
    }

    public void setEndMony(Long endMony) {
        this.endMony = endMony;
    }

    public String getNeedMode() {
        return needMode;
    }

    public void setNeedMode(String needMode) {
        this.needMode = needMode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
