package cc.mrbird.web.aliPay;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class testFaPiao {

    public static void main(String[] args) {

        //String publicKey=#替换发票管家分配的publickey#;
        //  String sign=#替换sign#;

        String publicKey="";
        String sign="";
        String token;
        try {
            token= URLEncoder.encode("#替换token#", "UTF-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
//
//        Map<String, String> params=new HashMap<String, String>();
//        params.put("einv_trade_id",#替换einv_trade_id#");
//                params.put("m_short_name", #替换m_short_name#);
//        params.put("random", #替换random#);
//        params.put("sub_m_short_name", #替换sub_m_short_name#);
//        params.put("timestamp", #替换timestamp#);
//        params.put("token", token);
//        System.out.println(ECCUtil.verify(publicKey, params, sign));

    }




}
