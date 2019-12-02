package cc.mrbird.web.dto.out;

import java.io.Serializable;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/2 16:38
 * @Description:
 */
public class LoginLogOut implements Serializable {



    private int allUserNums;

    private int oneDayLoginNums;


    public int getAllUserNums() {
        return allUserNums;
    }

    public void setAllUserNums(int allUserNums) {
        this.allUserNums = allUserNums;
    }

    public int getOneDayLoginNums() {
        return oneDayLoginNums;
    }

    public void setOneDayLoginNums(int oneDayLoginNums) {
        this.oneDayLoginNums = oneDayLoginNums;
    }
}
