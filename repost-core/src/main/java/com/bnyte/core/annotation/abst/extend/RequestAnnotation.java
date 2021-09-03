package com.bnyte.core.annotation.abst.extend;

import com.bnyte.core.annotation.abst.AbstractRepostAnnotation;
import com.bnyte.core.bind.enums.RequestMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RequestAnnotation<T> extends AbstractRepostAnnotation<T> {

    @Override
    public Map<String, Object> forMap(Object source, Class<?> annotationType) {
        Map<String, Object> forMap = super.forMap(source, annotationType);
        List<String> methodList = new LinkedList<>();

        Object methods = forMap.get("methods");
        if (methods instanceof LinkedList) {
            return forMap;
        }
        if (methods instanceof RequestMethod[]) {
            RequestMethod[] requestMethods = (RequestMethod[]) methods;
            for (RequestMethod requestMethod : requestMethods) {
                methodList.add(requestMethod.name());
            }
        }

        forMap.put("methods", methodList);

        return forMap;
    }

    @Override
    public Object getValue(Object source, Class<?> annotationType, String propertiesName) {
        return super.getValue(source, annotationType, propertiesName);
    }
}
