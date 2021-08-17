package com.bnyte.core.context;

import com.bnyte.core.cache.InterfaceCache;

/**
 * @auther bnyte
 * @date 2021-08-17 17:10
 * @email bnytezz@163.com
 */
public class RepostRequest {

    public static InterfaceCache<String, RepostInterface> interfaceCache;

    public static InterfaceCache<String, RepostInterface> getInterfaceCache() {
        return interfaceCache;
    }

    public static void setInterfaceCache(InterfaceCache<String, RepostInterface> interfaceCache) {
        RepostRequest.interfaceCache = interfaceCache;
    }
}
