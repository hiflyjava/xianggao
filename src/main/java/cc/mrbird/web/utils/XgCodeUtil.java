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
    public static  final  String ORDER_STATUS_BUILD="I";//刚刚创建；还没有设计师审核

    //订单支付状态
    public static  final  String ORDER_PAY_STATUS_YES="Y";//已经支付
    public static  final  String ORDER_PAY_STATUS_NO="N";//未支付

    //真正支付状态
    public static  final  String PAY_ORDER_PAY_STATUS_YES="Y";//已经支付
    public static  final  String PAY_ORDER_PAY_STATUS_NO="N";//未支付

    //支付方式
    public static  final  String PAY_TYPE_WX="WX";//微信
    public static  final  String PAY_TYPE_ZFB="ZFB";//支付宝

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


    //需求的模式：ZB招标，HCT画草图，BG比稿，GY雇佣
    public  static  final  String NEED_MODEL_ZB="ZB";
    public  static  final  String NEED_MODEL_HCT="HCT";
    public  static  final  String NEED_MODEL_BG="BG";
    public  static  final  String NEED_MODEL_GY="GY";//雇佣

    //需求的审核状态
    public  static  final  String NEED_CHECK_STATUS_INIT="I";//需求初始化状态
    public  static  final  String NEED_CHECK_STATUS_OK="A";//需求审核通过
    public  static  final  String NEED_CHECK_STATUS_FAILED="F";//需求审核失败

    //需求的交易状态
    public static  final  String NEED_PAY_STATUS_INIT="I";//没开始
    public static  final  String NEED_PAY_STATUS_ING="A";//正在匹配中
    public static  final  String NEED_PAY_STATUS_IS_WORKING="W";//正在工作中
    public static  final  String NEED_PAY_STATUS_INIT_END="S";//交易完成

     //用户类型

    public static final  String  USER_TYPE_ONE="ONE";
    public static final String   USER_TYPE_COMPANY="COMPANY";



}
