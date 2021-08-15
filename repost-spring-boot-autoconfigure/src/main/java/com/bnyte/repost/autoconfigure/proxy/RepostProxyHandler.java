package com.bnyte.repost.autoconfigure.proxy;

import com.bnyte.core.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author bnyte
 * @version 1.0.0
 * @date 2021-08-15 20:22
 */
public class RepostProxyHandler<T> implements InvocationHandler {

    static Logger log = LoggerFactory.getLogger(RepostProxyHandler.class);

    private Class<T> interfaceType;
    public static Object target;


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
//        Object invoke = method.invoke(target, args);
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            StringBuilder url = new StringBuilder("http://localhost:8080/");
            // 拿参数拼接请求URL
            log.info("[Repost] invoke " + method.getName() + " return result is " + null + "and args is " + Arrays.toString(args));

            for (Object arg : args) {
                url.append(arg);
            }
            String send = SendRequest.send(url.toString());
            return send;
        }
    }

}
