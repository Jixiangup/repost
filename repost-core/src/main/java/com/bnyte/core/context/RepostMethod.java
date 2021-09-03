package com.bnyte.core.context;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:41
 */
public class RepostMethod<K, P> extends HashMap<K, Method> {

    private String uri; // 当前请求资源
    private K methodId;
    private int parameterCount;
    private Annotation[] annotations;
    private RepostParameter<P> repostParameter;
    private boolean enableCache;
    private String url;

    public RepostMethod(Method method, K methodId, P parameters) {
        init(method, methodId, parameters);
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

    public K getMethodId() {
        return methodId;
    }

    public void setMethodId(K methodId) {
        this.methodId = methodId;
    }

    public RepostParameter<P> getRepostParameter() {
        return repostParameter;
    }

    public void setRepostParameter(RepostParameter<P> repostParameter) {
        this.repostParameter = repostParameter;
    }

    protected void initBefore(Method method) {
        String methodId = method.getName();

    }

    /**
     * 初始化接口方法类
     * @param method 当前执行的方法
     * @return
     */
    protected void init(Method method, K methodId, P parameters) {
        this.methodId = methodId;

        RepostMethod<String, List<Object>> currentMethod;

        Annotation[] annotations = method.getAnnotations();

        RepostParameter<List<Object>> repostParameter = new RepostParameter<>(methodId, Arrays.asList(parameters));

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
