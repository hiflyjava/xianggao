package cc.mrbird.web.dto.in;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/11/23 13:42
 * @Description:
 */
@ApiModel
public class XgProductionPageIn implements Serializable {

    @ApiModelProperty(value = "分页大小",example ="2000")
    private  int pageSize=2000000;
    @ApiModelProperty(value = "分页页数",example ="2000")
    private int currentPage=1;


    @ApiModelProperty(value = "用户名",example ="2000")
    private String username;
    @ApiModelProperty(value = "开始时间",example ="2000")
    private String startTime;
    @ApiModelProperty(value = "结束时间",example ="2000")
    private String endTime;
    @ApiModelProperty(value = "类型",example ="2000")
    private String type;
    @ApiModelProperty(value = "状态值",example ="2000")
    private String status;


    private Long userId;

    private int selectType;
    @ApiModelProperty(value = "订单号",example ="2000")
    private String orderNum;//
    @ApiModelProperty(value = "",example ="2000")
    private Long id;
    @ApiModelProperty(value = "给谁点赞",example ="2000")
    private  Long toUserId;//


    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getSelectType() {
        return selectType;
    }

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
