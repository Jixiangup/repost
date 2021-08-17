package com.bnyte.core.handler;


import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.context.RepostInterface;

import java.lang.reflect.Method;

/**
 * @auther bnyte
 * @date 2021-08-16 18:23
 * @email bnytezz@163.com
 */
public abstract class AbstractRepostRequest {
    public Method method;
    public Class<?> interfaceType;
    public Object[] parameters;
    public InterfaceCache<String, RepostInterface> interfaceCache;

    public AbstractRepostRequest(Method method, Class<?> interfaceType, Object[] parameters) {
        this.method = method;
        this.interfaceType = interfaceType;
        this.parameters = parameters;
    }

    /**
     * 初始化之前操作
     */
    public void initRepostRequestBefore() {

    }

    /**
     * 初始化
     */
    public void init() {

    }

    /**
     * 初始化之后
     */
    public void initAfter() {

    }

}
