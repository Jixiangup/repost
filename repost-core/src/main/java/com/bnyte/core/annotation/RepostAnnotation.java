package com.bnyte.core.annotation;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @auther bnyte
 * @date 2021-08-18 11:49
 * @email bnytezz@163.com
 */
public interface RepostAnnotation<T> {

    /**
     * 通过源对象获取指定的注解的所有属性值和名
     * @param source 源对象
     * @return 返回由key为属性名，value为属性值组成的map集合
     */
    Map<String, Object> forMap (Object source, Class<?> annotationType);

    /**
     * 通过源对象获取指定的注解的指定属性值
     * @param source 源对象
     * @param annotationType 注解类型
     * @param propertiesName 属性名
     * @return 返回指定属性名的value值
     */
    Object getValue (Object source, Class<?> annotationType, String propertiesName);



}
