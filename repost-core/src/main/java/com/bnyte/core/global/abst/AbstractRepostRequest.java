package com.bnyte.core.global.abst;

import com.bnyte.core.global.RepostRequest;

import java.lang.reflect.Method;

/**
 * @auther bnyte
 * @date 2021-08-16 18:23
 * @email bnytezz@163.com
 */
public abstract class AbstractRepostRequest implements RepostRequest {
    public Method method;
    public Class<?> interfaceType;
    public Object[] parameters;

    public AbstractRepostRequest(Method method, Class<?> interfaceType, Object[] parameters) {
        this.method = method;
        this.interfaceType = interfaceType;
        this.parameters = parameters;
    }

}
