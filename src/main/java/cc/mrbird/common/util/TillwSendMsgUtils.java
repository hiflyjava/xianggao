package cc.mrbird.common.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * @Auther: admin
 * @Date: 2019/8/29 18:47
 * @Description:
 */
public class TillwSendMsgUtils {



    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACa358d0439c612eaefc680188709abd4c";
    public static final String AUTH_TOKEN = "d5338823e3b17b70b9d8f8e0322746fa";
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//
//        Message message = Message
//                .creator(new PhoneNumber("+8615675311927"), new PhoneNumber("+12092578450"),
//                        "Tomorrow's forecast in Financial District, San Francisco is Clear")
//                .setMediaUrl("http://twimlets.com/holdmusic?Bucket=com.twilio.music.ambient")
//                .create();
//        System.out.println(message.getSid());
//    }

}
