package com.bnyte.core.global;

import java.lang.reflect.Method;

public interface RepostRequest {

    /**
     * 当前代理的方法
     */
    Method method = null;

    /**
     * 当前代理的接口
     */
    Class<?> interfaceType = null;

    /**
     * 当前代理的方法中的请求参数
     */
    Object[] parameters = null;

    /**
     * 具体执行的请求URL
     */
    StringBuilder finalUrl = null;

    /**
     * 方法的请求体
     */
    BodyParameter finalBody = null;




}
