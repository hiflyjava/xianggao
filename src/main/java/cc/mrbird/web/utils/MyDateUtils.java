package cc.mrbird.web.utils;

import org.apache.poi.hslf.record.CString;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils implements Serializable {



    public static String getDifferDateByInt(String flag,String startTime){


        SimpleDateFormat formatIn= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            Date startDate = formatIn.parse(startTime);

            //当天
            if (XgCodeUtil.TODAT.equals(flag)){
                c.setTime(startDate);
                String end = format.format(c.getTime())+" 00:00:00";
                //  String day = format.format(d)+"00:00:00";
                System.out.println("当天开始时间："+end);
                return  end;
            }


            //1.过去七天
            if (XgCodeUtil.ONE_WEEK.equals(flag)){
                c.setTime(startDate);
                c.add(Calendar.DATE, - 7);
                String end = format.format(c.getTime())+" 00:00:00";
                //  String day = format.format(d)+"00:00:00";
                System.out.println("过去七天："+end);
                return  end;
            }

            // 2.过去一月
            if(XgCodeUtil.ONE_MOUTH.equals(flag)){
                c.setTime(startDate);
                c.add(Calendar.MONTH, -1);
                //Date m = c.getTime();
                String mon = format.format(c.getTime())+" 00:00:00";
                System.out.println("过去一个月："+mon);
                return  mon;
            }

            // 2.过去半年
            if(XgCodeUtil.ONE_HELF_YEAR.equals(flag)){
                c.setTime(startDate);
                c.add(Calendar.MONTH, -6);
                // Date m = c.getTime();
                String helfYear = format.format(c.getTime())+" 00:00:00";
                System.out.println("过去半年："+helfYear);
                return  helfYear;
            }


            // 2.过去1年
            if(XgCodeUtil.ONE_YEAR.equals(flag)){
                c.setTime(startDate);
                c.add(Calendar.YEAR, -1);
                //Date m = c.getTime();
                String oneYear = format.format(c.getTime())+" 00:00:00";
                System.out.println("过去1年："+oneYear);
                return  oneYear;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }





        return null;
    }


    //得到两个日期的相差天数
    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
        } catch (ParseException e)
        {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return day;
    }


//    public static void main(String[] args) {
//        String date = getDifferDateByInt(XgCodeUtil.TODAT,"2019-11-12 00:00:00");
//        System.out.println(date);
//    }


    public static  String getStringDate(Date date){
        SimpleDateFormat s =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = s.format(date);
        // 时间戳转换日期
        return format;
    }


    //得到今天的零时时间
    public static String  getTodayZeroTime (){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat s =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = s.format(zero);
        return  format;
    }
    //得到今天23点时间
    public static  String getTodayEndTime(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 59);
        Date zero1 = calendar1.getTime();
        SimpleDateFormat s =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = s.format(zero1);
        // 时间戳转换日期
        return format;

       // return zero1;
    }


}
