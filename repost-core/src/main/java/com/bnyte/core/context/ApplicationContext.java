package com.bnyte.core.context;

import com.bnyte.core.cache.RepostCache;
import com.bnyte.core.config.RepostConfig;

/**
 * 全文上下文接口定义
 * @auther bnyte
 * @date 2021-09-06 11:40
 * @email bnytezz@163.com
 */
public interface ApplicationContext {
    /**
     * 获取Repost应用上下文中的配置类
     * @return
     */
    RepostConfig getRepostConfig ();

    /**
     * 获取Repost应用上下文中的缓存对象
     */
    RepostCache getRepostCache () ;

}
