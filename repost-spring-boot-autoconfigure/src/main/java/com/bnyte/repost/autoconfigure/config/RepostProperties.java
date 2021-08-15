package com.bnyte.repost.autoconfigure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bnyte.repost")
public class RepostProperties {

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
    private Long connect = 3000L;

    /**
     * 全局扫描，TRUE为全局扫描，FALSE只会扫描指定包下，默认FALSE
     */
    private boolean allInterfaces = true;

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

    public Long getConnect() {
        return connect;
    }

    public void setConnect(Long connect) {
        this.connect = connect;
    }
}
