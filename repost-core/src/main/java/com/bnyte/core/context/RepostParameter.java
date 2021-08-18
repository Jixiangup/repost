package com.bnyte.core.context;

import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 * @auther bnyte
 * @date 2021-08-17 13:31
 * @email bnytezz@163.com
 */
public class RepostParameter<T> extends HashMap<String, T> {

    private Parameter[] parameters;

    public RepostParameter(Parameter[] parameters) {
        super();
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public static RepostParameter<Object> initRepostParameter (Parameter[] parameters) {
        RepostParameter<Object> parameter = new RepostParameter<>(parameters);
        return parameter;
    }
}
