package com.bnyte.core;

import com.alibaba.fastjson.JSON;
import com.bnyte.core.annotation.RepostAnnotation;
import com.bnyte.core.annotation.target.RequestAnnotation;
import com.bnyte.core.bind.annotation.Request;
import com.bnyte.core.bind.annotation.RequestMethod;
import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.context.RepostInterface;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @auther bnyte
 * @date 2021-08-17 13:20
 * @email bnytezz@163.com
 */
@Request(
        value = "http://localhost:8080/value", url = "http://localhost:8080/url",
        methods = RequestMethod.GET, enableCache = true
)
public class TestDemo {

    @Test
    public void test() throws Exception {
        InterfaceCache<String, RepostInterface> interfaceCache = new InterfaceCache<>();
        interfaceCache.put("testController", new RepostInterface());
        System.out.println(interfaceCache.get(""));
        System.out.println(interfaceCache.size());
        System.out.println(interfaceCache.isEmpty());
    }

    @Test
    public void test01() throws Exception {
        Map<String, Object> result = new HashMap<>();
        RepostConfig repostConfig = new RepostConfig();
        result.put(null, repostConfig);
        String string = JSON.toJSONString(result);
        System.out.println(string);
    }

    @Test
    public void test02() throws Exception {
        Map<String, Object> result = new HashMap<>();
        Annotation[] annotations = this.getClass().getAnnotations();

        for (Annotation annotation : annotations) {
            InvocationHandler handler = Proxy.getInvocationHandler(annotation);
            Field declaredFields = handler.getClass().getDeclaredField("memberValues");
            declaredFields.setAccessible(true);
            LinkedHashMap valueObject = (LinkedHashMap) declaredFields.get(handler);
            for (Object ley : valueObject.keySet()) {
                System.out.println(ley);
            }
            Object value = valueObject.get("value");
            Object url = valueObject.get("url");
            Object enableCache = valueObject.get("enableCache");
            RequestMethod[] methods = (RequestMethod[]) valueObject.get("methods");

            System.out.println("value >> " + value +
                    ", enableCache >> " + enableCache +
                    ", url >> " + url +
                    ", methods >> " + methods[0].name());
            System.out.println(RequestMethod.GET.name().equals(methods[0].name()));

            System.out.println(valueObject);

        }
        System.out.println(result);
    }

    @Test
    public void test03 () throws Exception {
        RepostAnnotation<Request> requestAnnotation = new RequestAnnotation<>();
        Map<String, Object> map = requestAnnotation.forMap(this, Request.class);
        Object methods = requestAnnotation.getValue(this, Request.class, "1");
        System.out.println(methods == null);
    }

    @Test
    public void test04 () throws Exception {
        System.out.println(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
