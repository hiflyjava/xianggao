package cc.mrbird.web.utils;

import java.io.Serializable;

public class XgCodeUtil implements Serializable {




    public static  final  String TODAT="TODAY";

    public static  final  String ONE_WEEK="ONE_WEEK";

    public static  final  String ONE_MOUTH="ONE_MOUTH";
    public static  final  String ONE_HELF_YEAR="ONE_HELF_YEAR";
    public static  final  String ONE_YEAR="ONE_YEAR";


    //订单状态

    public static  final  String ORDER_STATUS_INIT="A";//进行中
    public static  final  String ORDER_STATUS_SUCCESS="O";//成功
    public static  final  String ORDER_STATUS_CANCLE="C";//取消



    //浏览器状态；
    public  static  final  String WEB_STATUS_ON="ON";//连接状态
    public  static  final  String WEB_STATUS_OFF="OFF";//未连接状态

    public  static  final  String WEB_STATUS="STATUS_";//未连接状态

    // 作品状态:A:正常；B:首推；C：符合违反国家；D:涉黄；E:黑名单；I初始化状态
    public  static  final  String PRODUCTION_STATUS_A="A";//正常

    public  static  final  String PRODUCTION_STATUS_B="B";//首推
    public  static  final  String PRODUCTION_STATUS_C="C";//不符合违反国家
    public  static  final  String PRODUCTION_STATUS_D="D";//涉黄
    public  static  final  String PRODUCTION_STATUS_E="E";//黑名单
    public  static  final  String PRODUCTION_STATUS_I="I";//I初始化状态


}
