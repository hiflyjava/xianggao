package cc.mrbird.web.domain;

import cc.mrbird.system.domain.User;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class XgProduction  implements Serializable {

    private static final long serialVersionUID = -6051593356331868324L;
    private Long id;

    private String name;

    private String notes;//作品说明

    private int type;

    private String firstImg;

    private String imgs;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String checkedBy;

    private String extend2;

    private String extend3;

    private String label;

    private String status;

    private Long userId;

    private Date checkedDate;

    private String attributes;//作品属性 作品属性1:原创；2:临摹；3：分享


    private String username;

    private String isVip;

    private String companyId;

    private String productionType;

    private int pdzCount;//点赞总数；

    private List<XgProductionDianzan> xgProductionDianzans;//点赞

    private List<XgProductionCollection> xgProductionCollections;//收藏

    private  List<XgProductionShare> xgProductionShares;//分享

    private User user;

    @Transient
    private String userImg;//该作品主人的 头像

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPdzCount() {
        return pdzCount;
    }

    public void setPdzCount(int pdzCount) {
        this.pdzCount = pdzCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public List<XgProductionDianzan> getXgProductionDianzans() {
        return xgProductionDianzans;
    }

    public void setXgProductionDianzans(List<XgProductionDianzan> xgProductionDianzans) {
        this.xgProductionDianzans = xgProductionDianzans;
    }

    public List<XgProductionCollection> getXgProductionCollections() {
        return xgProductionCollections;
    }

    public void setXgProductionCollections(List<XgProductionCollection> xgProductionCollections) {
        this.xgProductionCollections = xgProductionCollections;
    }

    public List<XgProductionShare> getXgProductionShares() {
        return xgProductionShares;
    }

    public void setXgProductionShares(List<XgProductionShare> xgProductionShares) {
        this.xgProductionShares = xgProductionShares;
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg == null ? null : firstImg.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
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
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }


    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}