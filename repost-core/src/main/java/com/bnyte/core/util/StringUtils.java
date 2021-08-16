package com.bnyte.core.util;

/**
 * @auther bnyte
 * @date 2021-08-16 18:33
 * @email bnytezz@163.com
 */
public class StringUtils {

    public static boolean hasText(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof String)) {
            return false;
        }

        String tmp = String.valueOf(obj);

        return tmp != null && tmp.trim().length() != 0;
    }

}
