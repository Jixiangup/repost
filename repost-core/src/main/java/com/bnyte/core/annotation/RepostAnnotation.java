package com.bnyte.core.annotation;

import java.util.Map;

/**
 * @auther bnyte
 * @date 2021-08-18 11:49
 * @email bnytezz@163.com
 */
public interface RepostAnnotation<T> {

    T parseToAnnotation (Class<?> annotationClass);

    Map<String, Object> forMap (Class<?> annotationClass);



}
