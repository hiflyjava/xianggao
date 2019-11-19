package cc.mrbird.common.aws;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//





import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    public static boolean equalsNumber(String number) {
        String regex = "^-?[1-9]\\d*$";
        return number.matches(regex);
    }

    public static boolean isEmptyOrNull(CharSequence str) {
        return str == null || str.length() == 0 || str.equals("null") || "".equals(str);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String getPwd(String pwd) {
        String tempPwd = "";

        for(int i = 0; i < pwd.length(); ++i) {
            if (i == pwd.length() / 2 - 1) {
                tempPwd = tempPwd + pwd.charAt(i) + " - ";
            } else {
                tempPwd = tempPwd + pwd.charAt(i);
            }
        }

        return tempPwd;
    }

    public static String getPwdAsterisk(String pwd) {
        StringBuffer tempPwd = new StringBuffer();
        if (pwd.length() > 2) {
            tempPwd.append(pwd.substring(0, 2));
        }

        tempPwd.append("**");
        if (pwd.length() > 4) {
            int l = pwd.length();
            tempPwd.append(pwd.substring(l - 2, l));
        }

        return tempPwd.toString();
    }

    public static String parseAckData(String data) {
        String result = data;
        String reverse = (new StringBuffer(data)).reverse().toString();
        Pattern p = Pattern.compile("[^0]{1}");
        Matcher m = p.matcher(reverse);
        if (m.find()) {
            int index = Integer.parseInt(m.group(), 16);
            int endIndex = m.start();
            if (endIndex > 1) {
                if (index >= 1 && endIndex == index * 2) {
                    result = data.substring(0, data.length() - (endIndex + 2));
                } else {
                    result = data;
                }
            }
        }

        return result;
    }

    public static List<String> getArrayList(int size, int min, int max) {
        LinkedHashSet stringSet = new LinkedHashSet();

        while(stringSet.size() < size) {
            Set<Integer> set = cc.mrbird.common.aws.RandomUtils.generateRandom(size, min, max);
            StringBuilder builder = new StringBuilder();
            Iterator var6 = set.iterator();

            while(var6.hasNext()) {
                Integer item = (Integer)var6.next();
                builder.append(item);
            }

            stringSet.add(builder.toString());
        }

        return new ArrayList(stringSet);
    }

    public static List<String> getSetArrayList(int size, int length, int range) {
        LinkedHashSet stringSet = new LinkedHashSet();

        while(stringSet.size() < size) {
            Set<Integer> set = cc.mrbird.common.aws.RandomUtils.generateRandomSet(length, range);
            StringBuilder builder = new StringBuilder();
            Iterator var6 = set.iterator();

            while(var6.hasNext()) {
                Integer item = (Integer)var6.next();
                builder.append(item);
            }

            stringSet.add(builder.toString());
        }

        return new ArrayList(stringSet);
    }

    public static List<String> getRepeatArrayList(int size, int length, int range) {
        LinkedHashSet stringSet = new LinkedHashSet();

        while(stringSet.size() < size) {
            List<Integer> list = RandomUtils.generateRepeatRandomList(length, range);
            StringBuilder builder = new StringBuilder();
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                Integer item = (Integer)var6.next();
                builder.append(item);
            }

            stringSet.add(builder.toString());
        }

        return new ArrayList(stringSet);
    }

    public static String getArrayString(List<String> arrayList) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < arrayList.size(); ++i) {
            String item = (String)arrayList.get(i);
            builder.append(item);
            if (i != arrayList.size() - 1) {
                builder.append(",");
            }
        }

        return builder.toString();
    }

    public static String listToString(List<String> arrayList) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < arrayList.size(); ++i) {
            String item = (String)arrayList.get(i);
            builder.append(item);
        }

        return builder.toString();
    }
}
