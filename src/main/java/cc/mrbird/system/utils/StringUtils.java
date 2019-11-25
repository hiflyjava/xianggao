package cc.mrbird.system.utils;

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


}

