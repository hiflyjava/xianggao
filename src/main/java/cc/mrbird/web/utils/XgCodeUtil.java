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
}
