package com.bnyte.core.cache;

import com.bnyte.core.context.RepostInterface;
import com.bnyte.core.exception.RepostRuntimeException;
import com.bnyte.core.util.StringUtils;

import java.util.HashMap;

/**
 * @auther bnyte
 * @date 2021-08-17 13:16
 * @email bnytezz@163.com
 */
public class InterfaceCache<K, V> extends HashMap<K, V> {

    public static InterfaceCache<String, RepostInterface> interfaceCache;

    public static InterfaceCache<String, RepostInterface> getInterfaceCache() {
        return interfaceCache;
    }

    public static void setInterfaceCache(InterfaceCache<String, RepostInterface> interfaceCache) {
        InterfaceCache.interfaceCache = interfaceCache;
    }

    private String interfaceId; // 通过接口ID查询当前接口是否包含在缓存当中

    public InterfaceCache() {
        super();
    }


    /**
     * 如果能够get到说明一定在接口池里边，换句话说，在接口池一定就是开启了接口池，所以这里不在多做判断
     * @param interfaceId 接口ID
     * @return 返回接口对象
     */
    public V get(String interfaceId) {
        if (!StringUtils.hasText(interfaceId)) {
            throw new RepostRuntimeException("interface id cannot be empty, please check the incoming [interfaceId]");
        }
        return this.get((Object) interfaceId);
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public V put(K key, V value) {

        return super.put(key, value);
    }
}
