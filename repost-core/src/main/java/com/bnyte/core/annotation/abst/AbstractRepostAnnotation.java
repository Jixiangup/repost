package com.bnyte.core.annotation.abst;

import com.bnyte.core.annotation.RepostAnnotation;
import com.bnyte.core.exception.RepostRuntimeException;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractRepostAnnotation<T> implements RepostAnnotation<T> {

    @Override
    public Map<String, Object> forMap(Object source, Class<?> annotationType) {
        Map<String, Object> result = new LinkedHashMap<>();
        Class<?> sourceClass = source.getClass();
        if (!annotationType.isAnnotation()) {
            throw new RepostRuntimeException("the incoming parameter 'annotationType' is not an annotation");
        }
        Class<Annotation> annoType = (Class<Annotation>) annotationType;
        Annotation annotation = sourceClass.getAnnotation(annoType);
        InvocationHandler proxyAnnotation = Proxy.getInvocationHandler(annotation);
        try {
            Field field = proxyAnnotation.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Object keyValueObj = field.get(proxyAnnotation);
            if (keyValueObj instanceof LinkedHashMap) {
                result = (LinkedHashMap<String, Object>) keyValueObj;
            } else {
                throw new AnnotationFormatError("当前对象无法完成解析");
            }
            return result;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object getValue(Object source, Class<?> annotationType, String propertiesName) {
        return forMap(source, annotationType).get(propertiesName);
    }
}
