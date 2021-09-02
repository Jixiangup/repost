package com.bnyte.core.context;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;

/**
 * @auther bnyte
 * @date 2021-08-17 13:31
 * @email bnytezz@163.com
 */
public class RepostParameter<T> extends HashMap<String, T> {

    private Parameter[] parameters;

    public RepostParameter(String methodId, T parameters) {
        super();
        this.put(methodId, parameters);
    }

    @Override
    public T put(String methodId, T parameters) {
        return super.put(methodId, parameters);
    }

    public Object[] getParameters() {
        return parameters;
    }

    // TODO: 2021/9/2 编写parameter形参的封装
    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public RepostParameter<Object> initRepostParameter (String methodId, T parameters) {
        RepostParameter<T> repostParameter = new RepostParameter<>(methodId, parameters);
        return repostParameter;
    }
}
