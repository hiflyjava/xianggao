package cc.mrbird.web.utils;

import java.util.UUID;

/**
 * @Auther: admin
 * @Date: 2019/10/16 17:21
 * @Description:
 */
public class MyUUIDUtils {


    public static String getUUid(){


        UUID uuid=UUID.randomUUID();
        String uuidStr = uuid.toString();
        String uuidRep=uuidStr.replaceAll("-", "");
//        System.out.println("原始UUID："+uuid);
//        System.out.println("转成字符后的UUID:"+uuidStr);
//        System.out.println("去掉-的UUID字符串:"+uuidRep);
        return  uuidRep;
    }




    /**
     *
     * (获取指定长度uuid)
     *
     * @return
     */
    public static  String getUUIDByLen(int len)
    {
        if(0 >= len)
        {
            return null;
        }

        UUID uuid=UUID.randomUUID();
        String uuidStr = uuid.toString();
        String uuids=uuidStr.replaceAll("-", "");
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < len; i++)
        {
            str.append(uuids.charAt(i));
        }

        return str.toString();
    }



    public  static String  getEightNums(){
        String nums = (int) ((Math.random() * 9 + 1) * 10000000)+"";
        return  nums;

    }


//    public static void main(String[] args) {
//
//        String uUid = UUIDUtils.getUUid();
//        System.out.println(uUid);
//    }

}
