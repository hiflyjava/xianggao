package cc.mrbird.common.aws;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import org.apache.http.util.TextUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtils {
    public static final String NO_ZERO_NUMBERS = "123456789";
    private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private RandomUtils() {
        throw new AssertionError();
    }

    public static String getRandomNumbers(int length) {
        return getRandom("0123456789", length);
    }

    public static String getRandomLetters(int length) {
        return getRandom("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    public static String getRandomCapitalLetters(int length) {
        return getRandom("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }

    public static String getRandomLowerCaseLetters(int length) {
        return getRandom("abcdefghijklmnopqrstuvwxyz", length);
    }

    public static String getRandom(String source, int length) {
        return TextUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar != null && sourceChar.length != 0 && length >= 0) {
            StringBuilder str = new StringBuilder(length);
            Random random = new Random();

            for(int i = 0; i < length; ++i) {
                str.append(sourceChar[random.nextInt(sourceChar.length)]);
            }

            return str.toString();
        } else {
            return null;
        }
    }

    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        } else {
            return min == max ? min : min + (new Random()).nextInt(max - min);
        }
    }

    public static boolean shuffle(Object[] objArray) {
        return objArray == null ? false : shuffle(objArray, getRandom(objArray.length));
    }

    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray != null && shuffleCount >= 0 && (length = objArray.length) >= shuffleCount) {
            for(int i = 1; i <= shuffleCount; ++i) {
                int random = getRandom(length - i);
                Object temp = objArray[length - i];
                objArray[length - i] = objArray[random];
                objArray[random] = temp;
            }

            return true;
        } else {
            return false;
        }
    }

    public static int[] shuffle(int[] intArray) {
        return intArray == null ? null : shuffle(intArray, getRandom(intArray.length));
    }

    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray != null && shuffleCount >= 0 && (length = intArray.length) >= shuffleCount) {
            int[] out = new int[shuffleCount];

            for(int i = 1; i <= shuffleCount; ++i) {
                int random = getRandom(length - i);
                out[i - 1] = intArray[random];
                int temp = intArray[length - i];
                intArray[length - i] = intArray[random];
                intArray[random] = temp;
            }

            return out;
        } else {
            return null;
        }
    }

    public static int generateRandomNumber(int range) {
        Random ran = new Random();
        return ran.nextInt(range);
    }

    public static int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static Set<Integer> generateRandom(Integer size, int min, int max) {
        LinkedHashSet set = new LinkedHashSet();

        while(set.size() < size) {
            Integer temp = generateRandomNumber(min, max);
            set.add(temp);
        }

        return set;
    }

    public static Set<Integer> generateRandomSet(Integer size, Integer range) {
        Set<Integer> set = new LinkedHashSet();
        Random ran = new Random();

        while(set.size() < size) {
            Integer temp = ran.nextInt(range);
            set.add(temp);
        }

        return set;
    }

    public static List<Integer> generateRepeatRandomList(int size, int range) {
        List<Integer> list = new ArrayList();
        Random random = new Random();
        Integer temp = random.nextInt(range);

        while(list.size() < size) {
            list.add(temp);
        }

        return list;
    }
}
