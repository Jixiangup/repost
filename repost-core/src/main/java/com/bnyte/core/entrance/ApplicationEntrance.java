package com.bnyte.core.entrance;

import com.bnyte.core.cache.RepostCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.context.ApplicationContext;

/**
 * @auther bnyte
 * @date 2021-09-06 15:57
 * @email bnytezz@163.com
 */
public class ApplicationEntrance implements Entrance, ApplicationContext {

    RepostConfig config = getRepostConfig();

    @Override
    public Entrance initialize() {
        //
        return null;
    }

    @Override
    public Entrance interceptor() {
        return null;
    }

    @Override
    public Entrance execute() {
        return null;
    }

    @Override
    public byte[] originData() {
        return new byte[0];
    }


    @Override
    public RepostConfig getRepostConfig() {
        return RepostConfig.getRepostConfig();
    }

    @Override
    public RepostCache getRepostCache() {
        return this.config.getInterfaceCache();
    }
}
