package cc.mrbird.web.dto.out;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/2 19:55
 * @Description:
 */
public class IndexProductionOuts implements Serializable {


    private int shareNums;

    private int collectionNums;

    private int dianzanNums;

    private int replyNums;

    private int leaveMessageNums;

    private int fensi;

    private int sixin=1000;

    public int getSixin() {
        return sixin;
    }

    public void setSixin(int sixin) {
        this.sixin = sixin;
    }

    public int getShareNums() {
        return shareNums;
    }

    public void setShareNums(int shareNums) {
        this.shareNums = shareNums;
    }

    public int getCollectionNums() {
        return collectionNums;
    }

    public void setCollectionNums(int collectionNums) {
        this.collectionNums = collectionNums;
    }

    public int getDianzanNums() {
        return dianzanNums;
    }

    public void setDianzanNums(int dianzanNums) {
        this.dianzanNums = dianzanNums;
    }

    public int getReplyNums() {
        return replyNums;
    }

    public void setReplyNums(int replyNums) {
        this.replyNums = replyNums;
    }

    public int getLeaveMessageNums() {
        return leaveMessageNums;
    }

    public void setLeaveMessageNums(int leaveMessageNums) {
        this.leaveMessageNums = leaveMessageNums;
    }

    public int getFensi() {
        return fensi;
    }

    public void setFensi(int fensi) {
        this.fensi = fensi;
    }
}
