package cc.mrbird.web.aliPay;



import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayEbppInvoiceTitleListGetRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayEbppInvoiceTitleListGetResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

import com.google.gson.Gson;

public class ZhuanZhangUtil {




    //关联配置文件
   // private static ResourceManager rm = ResourceManager.getInstance();

    private static String gateway="https://openapi.alipaydev.com/gateway.do";//支付宝网关
    private static String appid="2016101600701324";//阿里公共账户的id
    private static String private_key="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDw4b+acU+BjDqbYvvyhHHnTvdxFco+R7QUzhWshK0AMB5hm9sSjJOydmWovl3G5Doxjo2PNQuro78R3dcSl+W/YqjUs1SbCBz/75UHkHG5r+CsFlM3d29a2Di0U7ZpcIWWNiZXz5da0VcIMfhPgIlfYkPtuJpHBG9V9OtaiNEbBmeNnswCMaIJ2hx0q8nds/GoTCDQDkfMzYT6QPlQ8N7V30h0JFjDzPJs7AjXfovuA4G8c9rOl13HQIk9UUtpg1w4hjPYKjme4CK0y2MQXV4kVuN0K5b/EJDIvZqYP6c/C+fRWkqF6TXOh2SlvRat5aSA+CKAgwzOzSHlpFcj6VWTAgMBAAECggEAdZY96i6WGS9TDLdtNh3hXID32YRBjdWC2wUfH3BE42qZ6hwi/RMSpnOVfKIxNP4ESlzETbwfTlJI23fnnXP4+0w2Dl7cVjHHtkhF/8NLQNHSYy8iwFjVSwtx4tVanm4HSCR24za7M+ItEhsSk/yzEpTthAYxialokBVXLoLLUN0FBPIwNNHxP4i3ERp4i7mXDe2Te0/bSfs2w/Uv4+FUkAKDKE08i7LPYTcy56/e1xJt+gdUpoSwfFvWObW2AJP3udxRe1iQ/YxYKqe2+dGBCMBtYvth1c2ixqAB3m6ZQutJEOpmya2bpNo04LrdKIFuTj1hNKtzeRaBnDtsRw31AQKBgQD8lxGODIXllAHb2zeOaApxocVFLyT3o6BXoHnrnomUNHXnOAiYvGd/d8TSXg6kioyBnn/Xh/EqAUqod1iVJqkEIYLh8P4YjpQ+S0zCdKuheR0B80ydxxRVP4RRQSKsz2Qwo6sLAgITzEmifTL0ivx+4TtgPN8xq5PnnadaHhXNgwKBgQD0IjeMIZGRr5JLRop/I5OVmk19AXwc7u8TXegu2UBdwrUV3Y25TLsT7PhyuOs14XAsou+bNAKFcwZv/yubY1t2QkFjgpY7pTgQyH7Obm5ejBpFmN7vWco7PvFkft0HWvELxiSXgS3T9BxkalmgSPKRHAbk0QOfcDgNxKaw0upqsQKBgQCN/L25hACglRqWZBxOyN1WEwG5ak71hd1UZLkkYfIkhXjhiPYMJZxoYwMY3haNF/TzyA1FMv22BxPYr330Gc9Yqx3PtJChbUSX3+w+QydXlaVgu0uORaKAaTwQEPVU/x/q15YGkhJo3qVl2csS/C4DhMjDyHQOj8yMnmq8lbzb0QKBgQDBE7qoqffNp3Rk8kIgcQuMmr9D6QO2gkU0JOW2/3zHanD6/QDUFtox1Q1c9eCX+VZVBvvG3GhOEH173+wl9XtlWNsMD0AvpehkZZzcODwzrEwu1MsjsvicyJfy6d52rbbLoAZaiACti3dU90Mw3+BvYSIWnzNlSkQap3dJi1V30QKBgDjhs1IUAstR5SVIytIY8wmE3DamWFq/CZw3PH+v4R6js+Tp8RJ7wVYLw27PtFDNMcl2Imeh4P4TrV3RPO+KfMby34AEzOjz00vED84QzVhMZw1eUmUdDZwi/G+9DXCQGZL+Y3Nfuy7xudSzXcOUobnWViAMCPaTybbWcntDTkcJ";//私钥
    private static String input_charset="utf-8";//字段类型
    private static String ali_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp+55Sup1X9a3YHibpPHbssLlChXKCJxCCWlqGwUczLGCB5Ppt8vUFmqem0VNiiFS9K5tCf1dtjLGhmzKWNJ98qvLqKaeyUqHsIJ/Qwkxe65nDH2/+4YJQbGk9+qQsQtkHFI2+V9bzXOG7ED8olQZshvBWQPjgwJNBqNOl4uIOZCqElvq1qgR1YC5dTbSJjRikgYYMaPjX7LPGybFHsjuu4nO+bMbYUhRDcy1PUBTUJoN4JzFCtzHDI5liXel7IzxXOiKdcmxRhKg6EkBhB+ujcIfT1GaxNXLghGfVag4uL3JG20qK8ugld4Xf1soDvt6SZ/hXQbadbNAbfdqyhmDRwIDAQAB";//公钥

    private static AlipayClient alipayClient;
    private static ZhuanZhangUtil instance=new ZhuanZhangUtil();

    private ZhuanZhangUtil(){
        alipayClient=new DefaultAlipayClient(gateway, appid, private_key, "json",input_charset,ali_public_key,"RSA2");
    }

    public static ZhuanZhangUtil getInstance(){
        return instance;
    }
    /**
     * 支付宝向用户转账
     * @param bizNo  逻辑单号
     * @param amount 转账金额 "1.21"单位元
     * @param account 支付宝账号
     * @param userName 支付宝真实姓名
     * @return
     */
    public Map<String,String> alipay2User(String bizNo,String amount,String account,String userName){
        Map<String,String> resultMap=new HashMap<String,String>();
        AlipayVo vo = new AlipayVo();
        vo.setOut_biz_no("13452");
        vo.setPayee_type("ALIPAY_LOGONID");
        vo.setAmount("100.21");
        vo.setPayee_account("dpqngj5498@sandbox.com");
        vo.setPayer_show_name("沙箱环境");
        vo.setPayee_real_name("沙箱环境");
        vo.setRemark("支付宝转账");
        String json = new Gson().toJson(vo);
        // 设置请求参数
        AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
        alipayRequest.setBizContent(json);
        AlipayFundTransToaccountTransferResponse response=null;
        try {
            response = alipayClient.execute(alipayRequest);
            System.out.println(JSON.toJSONString(response));
            if("10000".equals(response.getCode())){
                resultMap.put("success", "true");
                resultMap.put("des", "转账成功");
            }else{
                resultMap.put("success", "false");
                resultMap.put("des", response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            resultMap.put("success", "false");
            resultMap.put("des", "转账失败！");
        }
        return resultMap;
    }




    public  static  void getTaiTouList() throws AlipayApiException {

        AlipayClient alipayClient = new DefaultAlipayClient
                ("https://openapi.alipay.com/gateway.do",
                        appid,
                        private_key,
                        "json",
                        "utf-8",
                        ali_public_key,
                        "RSA2");
        AlipayEbppInvoiceTitleListGetRequest request = new AlipayEbppInvoiceTitleListGetRequest();
        request.setBizContent("{" +
                "    \"user_id\":\"2088399922228483\"" +
                "  }");
        AlipayEbppInvoiceTitleListGetResponse response = alipayClient.execute(request,"accessToken");
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

    }



    
}
