package cn.demonk.hiddenimage.utils;

/**
 * Created by ligs on 16/6/7.
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str != null && str.length() > 0) {
            return false;
        }
        return true;
    }
}
