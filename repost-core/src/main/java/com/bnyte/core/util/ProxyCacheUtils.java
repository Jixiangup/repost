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
public class ProxyCacheUtils {

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

    /**
     * 通过类的字节码对象获取到当前类的ProxyCache注解对象，如果为空没有添加则会
     * 去拿ProxyCache的支持类，ProxyCacheSup类上的注解对象。
     * @param interfaceClass 接口类型
     * @return 返回ProxyCache的实例对象
     */
    public static ProxyCache getProxyCache (Class<?> interfaceClass) {
        ProxyCache proxyCache = null;
        if (interfaceClass == null) {
            return ProxyCacheSup.class.getAnnotation(ProxyCache.class);
        }

        proxyCache = interfaceClass.getAnnotation(ProxyCache.class);

        if (proxyCache == null) {
            return ProxyCacheSup.class.getAnnotation(ProxyCache.class);
        }

        return proxyCache;
    }

    /**
     * 通过接口获取当前的接口id, 此方法会优先判断是否打开了ProxyCache注解，
     * 然后拿他注解中的interfaceId通过interfaceId，如StringUtils.hasText(id)果interfaceId为空则会拿默认的接口类名，首字母小写的原则作为接口ID
     * @param interfaceType 接口
     * @return 接口ID
     */
    public static String getInterfaceId (Class<?> interfaceType) {
        ProxyCache proxyCache = getProxyCache(interfaceType);
        String id = proxyCache.id();
        id = StringUtils.hasText(id) ? id : ClassUtils.getBeanName(interfaceType);
        return id;
    }


}
