package cc.mrbird.common.aws;

import java.util.List;

/**
 * @Auther: admin
 * @Date: 2019/8/29 10:48
 * @Description:
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class LockProxy {
    private int scanMaxCycle;

    public LockProxy() {
    }



    public List<String> sendSMS(List<String> mobile, String message) {
        return SMSSender.getInstance().sendSMSMessage(mobile, message);
    }




}

