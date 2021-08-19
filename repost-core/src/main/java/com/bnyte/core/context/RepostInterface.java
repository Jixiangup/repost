package com.bnyte.core.context;

import com.bnyte.core.bind.annotation.Request;
import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.util.ClassUtils;

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

    // 初始化接口
    public static RepostInterface initRepostInterface (Class<?> interfaceType, Method method, Object[] parameters) {
        // 当前接口id，这是唯一标识
        String interfaceId = ClassUtils.getBeanName(interfaceType);
        Request request = interfaceType.getAnnotation(Request.class);
        boolean enableCache = request.enableCache();
        RepostInterface cache = null;
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

        }

        RepostMethod repostMethod = null;

        /*
            缓存中查到了数据，直接初始化方法不在乎接口上的一些东西
         */
        if (cache != null) {
            repostMethod = RepostMethod.initRequestMethod(method, cache);
        }
        // 当前接口上的所有注解
        Annotation[] annotations = interfaceType.getAnnotations();

        // 初始化当前接口执行的方法
        repostMethod = RepostMethod.initRequestMethod(method, cache);

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
