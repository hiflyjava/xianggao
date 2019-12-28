package cc.mrbird.web.dto.in;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/28 14:47
 * @Description:
 */
public class PayIn  implements Serializable {


    private String bizNo;

    private String amount;
    private String userName;
    private String account;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
