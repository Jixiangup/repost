package com.bnyte.core.context;

import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.util.ProxyCacheUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:39
 */
public class RepostInterface<M, P> {

    /**
     * RepostConfig全局配置
     */
    private static RepostConfig repostConfig = RepostConfig.getRepostConfig();

    private String interfaceId;
    private Class<?> interfaceType;
    private Annotation[] annotations;
    private RepostMethod<Method, P> repostMethod;
    // 包含了父类方法
    private Long methodCount;
    private boolean enableCache;

    public RepostInterface() {

    }

    public RepostInterface(Class<?> interfaceType, M method, P parameters) {
        init(interfaceType, method, parameters);
    }

    /**
     * 初始化接口
     * @param interfaceType 当前接口字节码对象
     * @param method 当前接口执行的方法
     * @param parameters 当前方法的请求参数
     */
    public void init(Class<?> interfaceType, M method, P parameters) {
        // 为类的属性赋值
        initProperty(interfaceType, method, parameters);
    }

    /**
     * 为当前接口类中的成员变量初始化
     * @param interfaceType 当前接口类型
     * @param method 当前请求执行的方法
     * @param parameters 请求参数
     */
    protected void initProperty(Class<?> interfaceType, M method, P parameters) {
        this.interfaceId = ProxyCacheUtils.getInterfaceId(interfaceType);
        this.interfaceType = interfaceType;
        this.annotations = interfaceType.getAnnotations();
        this.setMethodCount(interfaceType.getMethods());
        this.enableCache = ProxyCacheUtils.isEnableInterfaceCache(interfaceType);
        this.repostMethod = new RepostMethod<>(method, parameters);
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

    public RepostMethod<String, List<Object>> getRepostMethod() {
        return this.repostMethod;
    }

    public void setRepostMethod(RepostMethod<String, List<Object>> repostMethod) {
        this.repostMethod = repostMethod;
    }

    public Long getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(Method[] methods) {
        long count = Arrays.stream(methods)
                .filter(method -> !Object.class.equals(method.getDeclaringClass()))
                .count();
        this.methodCount = count;
    }
}
