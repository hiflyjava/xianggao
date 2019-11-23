package com.pg.common.utils;

import com.pingenie.smartlock.util.DataUtils;
import com.pingenie.smartlock.util.RandomUtils;
import com.pingenie.smartlock.util.TextUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Utils
 */
public class StringUtils {

    /**
     * 验证邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }


    /**
     * 验证邮箱格式是否正确
     */
    public static boolean equalsNumber(String number) {
        //匹配整数
        String regex = "^-?[1-9]\\d*$";
        return number.matches(regex);
    }

    public static boolean isEmptyOrNull(CharSequence str) {
        return str == null || str.length() == 0 || str.equals("null") || "".equals(str);
    }


    public static boolean notEmptyOrNull(CharSequence str) {
        return str != null && str.length() != 0 && !str.equals("null") && !"".equals(str);
    }

    /**
     * 判断是否数字
     *
     * @param str
     * @return
     */
    private static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    public static boolean isNumeric(String str) {
        boolean pwdIdIsNull = str == null || str.length() == 0;
        if (pwdIdIsNull) {
            return false;
        }
        return pattern.matcher(str).matches();
    }

    public static String getPwd(String pwd) {
        StringBuilder tempPwd = new StringBuilder();
        if (!TextUtils.isEmpty(pwd)) {
            for (int i = 0; i < pwd.length(); i++) {
                if (i == pwd.length() / 2 - 1) {
                    tempPwd.append(pwd.charAt(i)).append(" - ");
                } else {
                    tempPwd.append(pwd.charAt(i));
                }
            }
        }

        return tempPwd.toString();
    }

    /**
     * 密码密文显示 前两位和最后两位显示明文中间显示**
     *
     * @param pwd
     * @return
     */
    public static String getPwdAsterisk(String pwd) {
        StringBuilder tempPwd = new StringBuilder();
        if (!TextUtils.isEmpty(pwd)) {
            if (pwd.length() > 2) {
                tempPwd.append(pwd.substring(0, 2));
            }
            tempPwd.append("**");
            if (pwd.length() > 4) {
                int l = pwd.length();
                tempPwd.append(pwd.substring(l - 2, l));
            }
        }
        return tempPwd.toString();
    }

    public static String parseAckData(String data) {
        String result = data;
        String reverse = new StringBuffer(data).reverse().toString();
       // LogUtils.logDebug("reverse:" + DES3Utils.encryptLog(reverse));
        result = getString(data, result, reverse);
        return result;
    }

    public static String parseAckDataF0(String data) {
        String result = data;
        String reverse = new StringBuffer(data).reverse().toString();
      // LogUtils.logDebug("reverse:" + DES3Utils.encryptLog(reverse));
        if (reverse.startsWith("0f") || reverse.startsWith("0F")) {
            result = data.substring(0, data.length() - 2);
        } else {
            result = getString(data, result, reverse);
        }
        return result;
    }

    private static String getString(String data, String result, String reverse) {
        Pattern p = Pattern.compile("[^0]{1}");
        Matcher m = p.matcher(reverse);
        if (m.find()) {
            int index = Integer.parseInt(m.group(), 16);
            int endIndex = m.start();
          //  LogUtils.logDebug("first number: " + m.group() + ", index: " + m.start());
            if (endIndex > 1) {
                if (index >= 1 && endIndex == (index * 2)) {
                    result = data.substring(0, data.length() - (endIndex + 2));
                } else {
                    result = data;
                }
             //   LogUtils.logDebug("result:" + DES3Utils.encryptLog(result));
            }
        }
        return result;
    }

    public static List<String> getArrayList(int size, int min, int max) {
        Set<String> stringSet = new LinkedHashSet<>();
        while (stringSet.size() < size) {
            Set<Integer> set = RandomUtils.generateRandom(size, min, max);
            StringBuilder builder = new StringBuilder();
            for (Integer item : set) {
                builder.append(item);
            }
            stringSet.add(builder.toString());
        }
        return new ArrayList<>(stringSet);
    }

    public static List<String> getSetArrayList(int size, int length, int range) {
        Set<String> stringSet = new LinkedHashSet<>();
        while (stringSet.size() < size) {
            Set<Integer> set = RandomUtils.generateRandomSet(length, range);
            StringBuilder builder = new StringBuilder();
            for (Integer item : set) {
                builder.append(item);
            }
            stringSet.add(builder.toString());
        }
        return new ArrayList<>(stringSet);
    }

    public static List<String> getArrayC(int size, int length, int range) {
        Set<String> stringSet = new LinkedHashSet<>();
        while (stringSet.size() < size) {
            Set<Integer> set = RandomUtils.generateRandomSet(length, range);
            StringBuilder builder = new StringBuilder();
            for (Integer item : set) {
                builder.append(item);
            }
            int number = Integer.parseInt(builder.toString());
            if (number >= 100 && number <= 999) {
                stringSet.add(builder.toString());
            }
        }
        return new ArrayList<>(stringSet);
    }

    public static List<String> getRepeatArrayList(int size, int length, int range) {
        Set<String> stringSet = new LinkedHashSet<>();
        while (stringSet.size() < size) {
            List<Integer> list = RandomUtils.generateRepeatRandomList(length, range);
            StringBuilder builder = new StringBuilder();
            for (Integer item : list) {
                builder.append(item);
            }
            stringSet.add(builder.toString());
        }
        return new ArrayList<>(stringSet);
    }

    public static String getArrayString(List<String> arrayList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String item = arrayList.get(i);
            builder.append(item);
            if (i != arrayList.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static String listToString(List<String> arrayList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String item = arrayList.get(i);
            builder.append(item);
        }
        return builder.toString();
    }

    public static String listToStringHex(List<String> arrayList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String item = arrayList.get(i);
            String number = DataUtils.toHexString(item);
            if (number.length() == 1) {
                builder.append("0");
            }
            builder.append(number);
        }
        return builder.toString();
    }

}

