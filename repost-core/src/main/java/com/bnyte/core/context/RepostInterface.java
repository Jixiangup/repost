package com.bnyte.core.context;

import com.bnyte.core.bind.annotation.ProxyCache;
import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.util.ClassUtils;
import com.bnyte.core.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:39
 */
public class RepostInterface {

    /**
     * RepostConfig全局配置
     */
    private static RepostConfig repostConfig = RepostConfig.getRepostConfig();

    private String interfaceId;
    private Class<?> interfaceType;
    private Annotation[] annotations;
    private RepostMethod<String, Method> repostMethod;
    private int methodCount;
    private boolean enableCache;

    public RepostInterface() {
    }

    public RepostInterface(String interfaceId, Class<?> interfaceType, Annotation[] annotations, RepostMethod<String, Method> repostMethod, boolean enableCache) {
        this.interfaceId = interfaceId;
        this.interfaceType = interfaceType;
        this.annotations = annotations;
        this.repostMethod = repostMethod;
        this.enableCache = enableCache;
    }

    /**
     * 初始化接口
     * @param interfaceType 当前接口字节码对象
     * @param method 当前接口执行的方法
     * @param parameters 当前方法的请求参数
     * @return 返回Repost封装的接口对象
     */
    public static RepostInterface initRepostInterface (Class<?> interfaceType, Method method, Object[] parameters) {
        // 当前初始化好之后的接口
        RepostInterface cache = null;
        // 当前执行的接口
        RepostMethod<String, Method> repostMethod = null;
        ProxyCache proxyCache = interfaceType.getAnnotation(ProxyCache.class);
        // 当前接口id，这是唯一标识，用于储存在缓存中的key，会通过注解获取是否指定，如果没有指定则说明当前缓存为空
        String interfaceId = proxyCache.id();
        // 当前接口上的所有注解
        Annotation[] annotations = interfaceType.getAnnotations();
        if (!StringUtils.hasText(interfaceId)) {
            interfaceId = ClassUtils.getBeanName(interfaceType);
        }
        // 判断当前是否开启缓存
        boolean enableCache = proxyCache.enableCache();
        // 判断当前接口中是否开启缓存
        if (!enableCache) {
            // 如果本也没有开启则都配置文件中是否配置了开启
            enableCache = repostConfig.isEnableCache();
        }
        /*
          如果目前全局RepostRequest对象中的接口池为空那么则说明当前是所有接口第一次被调用，也就是说在这里如果调用接口池的相关方法会发生空指针的bug
          所以此时我们会读出当前类中的注解来判断是否自定义需要开启缓存，如果不需要，那Repost会去全局config拿数据判断是否配置了开启缓存，如果有配置
          那次是就会判断，如果依然没有开启则直接跳过为当前接口缓存初始化减少内存占用
          当然他们的优先级顺序分别是 @Repost(enable=true)其次是配置文件中的bnyte.repost.enable=true
         */
        if (enableCache) {
            /*
                如果开启了缓存优先判断当前接口缓存池中是否为空，如果为空则说明是第一次解析请求对象，所以需要为接口缓存池初始化避免浪费内存
             */
            if (InterfaceCache.getInterfaceCache() == null) {
                InterfaceCache.setInterfaceCache(new InterfaceCache<>());
            }
            else {
                // 从缓存获取
                cache = InterfaceCache.getInterfaceCache().get(interfaceId);
            }
        }
        // 没有开启缓存
        else {
            // 直接注册请求
        }

        /*
            缓存中查到了数据，去当前接口中拿缓存的方法
         */
        if (cache != null) {
//            repostMethod = RepostMethod.initRequestMethod(method, cache);
            repostMethod = InterfaceCache.getInterfaceCache().get(interfaceId).getRepostMethod();
        } else {
            // 初始化当前接口执行的方法
            repostMethod = RepostMethod.initRequestMethod(method, cache);
        }


        if (cache == null) {
            cache =
                    new RepostInterface(interfaceId, interfaceType, annotations, repostMethod, enableCache);
        }

        InterfaceCache.getInterfaceCache().put(interfaceId, cache);

        return cache;
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

    public RepostMethod<String, Method> getRepostMethod() {
        return this.repostMethod;
    }

    public void setRepostMethod(RepostMethod<String, Method> repostMethod) {
        this.repostMethod = repostMethod;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }
}
