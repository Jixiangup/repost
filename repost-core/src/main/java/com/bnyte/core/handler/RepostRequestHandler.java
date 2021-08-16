package com.bnyte.core.handler;

import com.bnyte.core.context.RepostInterface;
import com.bnyte.core.context.RepostMethod;
import com.bnyte.core.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @auther bnyte
 * @date 2021-08-16 18:18
 * @email bnytezz@163.com
 */
public class RepostRequestHandler extends AbstractRepostRequest {

    private String url;
    private RepostInterface repostInterface;
    private RepostMethod repostMethod;

    public RepostRequestHandler(Method method, Class<?> interfaceType, Object[] parameters) {
        super(method, interfaceType, parameters);
        initRepostRequestBefore();
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

    @Override
    public void initRepostRequestBefore() {
        Annotation[] annotations = interfaceType.getAnnotations();
        // 初始化当前接口的所有注解
        if (repostInterface == null) {
            repostInterface = new RepostInterface();
        }
        // TODO 指定接口注解集合

        // 指定
        //
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void initAfter() {
        super.initAfter();
    }
}
