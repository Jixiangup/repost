package com.bnyte.core.context;

import java.lang.annotation.Annotation;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:39
 */
public class RepostInterface {

    private String interfaceId;
    private Class<?> interfaceType;
    private Annotation[] annotations;
    private RepostMethod repostMethod;
    private int methodCount;
    private boolean enableCache;

    public RepostInterface() {
    }

    public RepostInterface(String interfaceId, Class<?> interfaceType, Annotation[] annotations, RepostMethod repostMethod, boolean enableCache) {
        this.interfaceId = interfaceId;
        this.interfaceType = interfaceType;
        this.annotations = annotations;
        this.repostMethod = repostMethod;
        this.enableCache = enableCache;
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Class<?> getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Class<?> interfaceType) {
        this.interfaceType = interfaceType;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public RepostMethod getRepostMethod() {
        return repostMethod;
    }

    public void setRepostMethod(RepostMethod repostMethod) {
        this.repostMethod = repostMethod;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }
}
