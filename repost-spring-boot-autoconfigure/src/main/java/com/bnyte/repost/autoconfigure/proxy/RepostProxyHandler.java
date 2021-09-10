package com.bnyte.repost.autoconfigure.proxy;

import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.entrance.ApplicationEntrance;
import com.bnyte.core.entrance.Entrance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author bnyte
 * @version 1.0.0
 * @date 2021-08-15 20:22
 */
public class RepostProxyHandler<T> implements InvocationHandler {

    static Logger log = LoggerFactory.getLogger(RepostProxyHandler.class);

    private RepostConfig repostConfig = RepostConfig.getRepostConfig();

    private Class<T> interfaceType;


    public RepostProxyHandler(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    /**
     * 当这个类被实例化之后会回调这个方法
     * @param proxy 动态代理对象
     * @param method 当前对象执行的方法
     * @param args 请求参数
     * @return 方法执行之后的结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            Entrance entrance = new ApplicationEntrance();
            entrance.initialize();
            entrance.interceptor();
            entrance.execute();
            return "proxy object execute target method...but now author framework writing is not completed";
        }
    }

}
