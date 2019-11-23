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
