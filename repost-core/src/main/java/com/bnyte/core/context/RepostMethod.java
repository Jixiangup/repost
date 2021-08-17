package com.bnyte.core.context;

import com.bnyte.core.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:41
 */
public class RepostMethod {

    private String uri; // 当前请求资源
    private String methodId;
    private int parameterCount;
    private Annotation[] annotations;
    private RepostParameter<Object> repostParameter;
    private String url;

    public RepostMethod(String methodId, Annotation[] annotations, RepostParameter<Object> repostParameter) {
        this.methodId = methodId;
        this.annotations = annotations;
        this.repostParameter = repostParameter;
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

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public RepostParameter<Object> getRepostParameter() {
        return repostParameter;
    }

    public void setRepostParameter(RepostParameter<Object> repostParameter) {
        this.repostParameter = repostParameter;
    }
}
