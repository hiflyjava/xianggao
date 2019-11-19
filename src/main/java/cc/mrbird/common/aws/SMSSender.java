package cc.mrbird.common.aws;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMSSender {
    private static SMSSender instance = null;
    private AmazonSNSClient snsClient;

    private SMSSender() {
    }

    public static SMSSender getInstance() {
        if (instance == null) {
            instance = new SMSSender();
            instance.init();
        }

        return instance;
    }

    public void init() {
        try {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAI7HPB6QY7OSHVHVQ", "VipHmpVzAM7kVJoagjIxctbeqD2c8NQ5p/2rThRw");
            this.snsClient = new AmazonSNSClient(awsCreds);
            this.snsClient.setEndpoint("https://sns.us-west-2.amazonaws.com");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public List<String> sendSMSMessage(List<String> phoneNums, String message) {

        List<String> results =new ArrayList<>();
        for(String num:phoneNums){

                System.out.println(num);
                System.out.println(message);
                Map<String, MessageAttributeValue> smsAttributes = new HashMap();
               // PublishResult results = this.snsClient.publish((new PublishRequest()).withMessage(message).withPhoneNumber(num).withMessageAttributes(smsAttributes));
                PublishResult result = this.snsClient.publish((new PublishRequest()).withMessage(message).withPhoneNumber(num).withMessageAttributes(smsAttributes));
                System.out.println(result.getSdkResponseMetadata());
                System.out.println(result.getMessageId());
                System.out.println(result.getSdkHttpMetadata());
                results.add(result.getMessageId());
        }
        return results;

    }
}
