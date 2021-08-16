package com.bnyte.core.definition;

import com.bnyte.core.bind.annotation.RequestMethod;

/**
 * @auther bnyte
 * @date 2021-08-16 16:31
 * @email bnytezz@163.com
 */
public interface RequestDefinition {

    /**
     * 请求地址
     */
    String url = "";

    /**
     * 请求方式，默认使用GET
     */
    RequestMethod methodType = RequestMethod.GET;

    /**
     * 当前请求Url是否需要缓存
     */
    boolean needCache = false;

    /**
     * 设置当前请求的请求地址
     */
    void setUrl (String url);

    /**
     * 设置请求方式, 默认使用GET方式
     */
    default void setMethodType (RequestMethod methodType) {}

    /**
     * 设置是否需要缓存
     * @param doNeed true为需要缓存，false为不需要缓存
     */
    default void setNeedCache (boolean doNeed) {}

    /**
     * 获取当前请求对象的请求地址
     */
    default String getUrl () {
        return url;
    }

    /**
     * 获取当前请求的请求方式
     */
    default RequestMethod getMethodType () {
        return methodType;
    }

    /**
     * 获取当前请求在完成封装之后是否需要添加到缓存中
     * @return
     */
    default boolean isNeedCache () {
        return needCache;
    }

}
