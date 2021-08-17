package com.bnyte.core.config;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:04
 */
public class RepostConfig {

    /**
     * 保存配置
     */
    private static RepostConfig repostConfig;

    /**
     * 指定超时时间，默认三千毫秒
     */
    private Long timeout = 3000L;

    /**
     * 指定后端，默认okhttp
     */
    private String backend = "okhttp";

    /**
     * 指定连接超时，默认三千毫秒
     */
    private Long connectTimeout = 3000L;

    /**
     * 全局扫描，TRUE为全局扫描，FALSE只会扫描指定包下，默认FALSE
     */
    private boolean allInterfaces = true;

    /**
     * 指定请求字符编码
     */
    private String charset = "UTF-8";

    /**
     * 是否开启缓存，false为不开启，true为开启。默认不开启
     */
    private boolean enableCache = false;

    public RepostConfig() {
    }

    public boolean isEnableCache() {
        return enableCache;
    }

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isAllInterfaces() {
        return allInterfaces;
    }

    public void setAllInterfaces(boolean allInterfaces) {
        this.allInterfaces = allInterfaces;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getBackend() {
        return backend;
    }

    public void setBackend(String backend) {
        this.backend = backend;
    }

    public Long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public static RepostConfig getRepostConfig() {
        return repostConfig;
    }

    public static void setRepostConfig(RepostConfig repostConfig) {
        RepostConfig.repostConfig = repostConfig;
    }
}
