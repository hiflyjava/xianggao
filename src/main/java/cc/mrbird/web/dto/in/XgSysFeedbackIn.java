package cc.mrbird.web.dto.in;

import java.io.Serializable;
import java.util.Date;

public class XgSysFeedbackIn implements Serializable {


    private String id;
    private Long suggestId;

    private String feedbackInfo;

    private String feedbackTitle;

    private Long userId;

    private Date createDate;

    private String createBy;

    private String type;

    private String extend2;

    private String extend3;

    private Long parentId;

    private String toUsername;

    private String fromUsername;

    private Long  toUserId;


    private String toUserImg;

    private String  fromUserImg;

    private String  suggestTitle;
    private String  suggestInfo;
    private Date  sCreateDate;
    private String  suggestionUserName;

    private String fType;

    private Date updateDate;

    private Long updateUserId;

   private String startTime;

   private String endTime;
   private int pageSize=10;
   private int currentPage=1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Long suggestId) {
        this.suggestId = suggestId;
    }

    public String getFeedbackInfo() {
        return feedbackInfo;
    }

    public void setFeedbackInfo(String feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserImg() {
        return toUserImg;
    }

    public void setToUserImg(String toUserImg) {
        this.toUserImg = toUserImg;
    }

    public String getFromUserImg() {
        return fromUserImg;
    }

    public void setFromUserImg(String fromUserImg) {
        this.fromUserImg = fromUserImg;
    }

    public String getSuggestTitle() {
        return suggestTitle;
    }

    public void setSuggestTitle(String suggestTitle) {
        this.suggestTitle = suggestTitle;
    }

    public String getSuggestInfo() {
        return suggestInfo;
    }

    public void setSuggestInfo(String suggestInfo) {
        this.suggestInfo = suggestInfo;
    }

    public Date getsCreateDate() {
        return sCreateDate;
    }

    public void setsCreateDate(Date sCreateDate) {
        this.sCreateDate = sCreateDate;
    }

    public String getSuggestionUserName() {
        return suggestionUserName;
    }

    public void setSuggestionUserName(String suggestionUserName) {
        this.suggestionUserName = suggestionUserName;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
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
