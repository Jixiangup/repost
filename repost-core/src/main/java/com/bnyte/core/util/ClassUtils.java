package com.bnyte.core.util;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-15 22:31
 */
public class ClassUtils {

    /**
     * 吧类的类名首字母小写其他不变返回
     * @return beanName
     */
    public static String getBeanName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
        return simpleName;
    }



}
