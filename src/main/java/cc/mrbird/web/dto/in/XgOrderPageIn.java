package cc.mrbird.web.dto.in;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/23 16:17
 * @Description:
 */
public class XgOrderPageIn implements Serializable {


    private int pageSize =10;

    private int currentPage=1;

    private String orderNo;

    private Long designUserId;//接需求的人的id

    private Long needUserId;//发需求的人id


    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getDesignUserId() {
        return designUserId;
    }

    public void setDesignUserId(Long designUserId) {
        this.designUserId = designUserId;
    }

    public Long getNeedUserId() {
        return needUserId;
    }

    public void setNeedUserId(Long needUserId) {
        this.needUserId = needUserId;
    }
}
