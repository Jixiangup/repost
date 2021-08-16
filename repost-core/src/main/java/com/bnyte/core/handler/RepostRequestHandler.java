package com.bnyte.core.handler;

import com.bnyte.core.global.RepostRequest;
import com.bnyte.core.global.abst.AbstractRepostRequest;
import com.bnyte.core.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @auther bnyte
 * @date 2021-08-16 18:18
 * @email bnytezz@163.com
 */
public class RepostRequestHandler extends AbstractRepostRequest {

    private String url;

    public RepostRequestHandler(Method method, Class<?> interfaceType, Object[] parameters) {
        super(method, interfaceType, parameters);
        handlerUrl();
    }

    // TODO: 2021/8/16 获取url处理URL
    public String handlerUrl () {
        if (StringUtils.hasText(url)) {

        }
        return url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
