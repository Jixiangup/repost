package com.bnyte.core.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:41
 */
public class RepostMethod<T, V> extends HashMap<T, V> {

    private String uri; // 当前请求资源
    private T methodId;
    private int parameterCount;
    private Annotation[] annotations;
    private RepostParameter<Object> repostParameter;
    private boolean enableCache;
    private String url;

    public RepostMethod(T methodId, Annotation[] annotations, RepostParameter<Object> repostParameter, V method) {
        this.methodId = methodId;
        this.annotations = annotations;
        this.repostParameter = repostParameter;
        this.put(methodId, method);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public void setParameterCount(int parameterCount) {
        this.parameterCount = parameterCount;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public T getMethodId() {
        return methodId;
    }

    public void setMethodId(T methodId) {
        this.methodId = methodId;
    }

    public RepostParameter<Object> getRepostParameter() {
        return repostParameter;
    }

    public void setRepostParameter(RepostParameter<Object> repostParameter) {
        this.repostParameter = repostParameter;
    }

    /**
     * 初始化接口方法类
     * @param method 当前执行的方法
     * @return
     */
    public static RepostMethod<String, Method> initRequestMethod (Method method, Object[] parameters) {
        String methodId = method.getName();

        RepostMethod<String, Method> currentMethod;

        Annotation[] annotations = method.getAnnotations();

        RepostParameter<Object> repostParameter = RepostParameter.initRepostParameter(methodId, Arrays.asList(parameters));

        currentMethod = new RepostMethod<>(methodId, annotations, repostParameter, method);

        return currentMethod;
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }
}
