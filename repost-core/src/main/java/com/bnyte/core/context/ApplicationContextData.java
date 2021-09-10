package com.bnyte.core.context;

import com.bnyte.core.cache.RepostCache;
import com.bnyte.core.config.RepostConfig;

/**
 * @auther bnyte
 * @date 2021-09-06 15:45
 * @email bnytezz@163.com
 */
public class ApplicationContextData implements ApplicationContext {

    RepostConfig config = getRepostConfig();

    @Override
    public RepostConfig getRepostConfig() {
        return RepostConfig.getRepostConfig();
    }

    @Override
    public RepostCache getRepostCache() {
        return config.getInterfaceCache();
    }
}
