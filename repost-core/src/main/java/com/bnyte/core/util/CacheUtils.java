package com.bnyte.core.util;

import com.bnyte.core.bind.annotation.ProxyCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.support.ProxyCacheSup;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * 缓存处理工具类
 * @auther bnyte
 * @date 2021-09-02 14:08
 * @email bnytezz@163.com
 */
public class CacheUtils {

    private static RepostConfig config = RepostConfig.getRepostConfig();

    /**
     * 判断当前接口是否开启缓存
     * @return true为开启了缓存，false为没有开启
     */
    public static boolean isEnableInterfaceCache (@NotNull Class<?> clazz) {
        return cacheCore(clazz, null);
    }

    /**
     * 判断当前方法否开启缓存
     * @return true为开启了缓存，false为没有开启
     */
    public static boolean isEnableMethodCache (@NotNull Method method) {
        return cacheCore(null, method);
    }

    private static boolean cacheCore(Class<?> interfaceClass, Method method) {
        boolean isEnableCache = false;
        ProxyCache proxyCache = null;
        if (interfaceClass != null) {
            proxyCache = interfaceClass.getAnnotation(ProxyCache.class);
        } else {
            proxyCache = method.getAnnotation(ProxyCache.class);
        }
        if (proxyCache == null) {
            proxyCache = ProxyCacheSup.class.getAnnotation(ProxyCache.class);
        }
        isEnableCache = proxyCache.enableCache();
        if (!isEnableCache) {
            isEnableCache = config.isEnableCache();
        }
        return isEnableCache;
    }
}
