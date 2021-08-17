package com.bnyte.core.context;

import java.util.HashMap;

/**
 * @auther bnyte
 * @date 2021-08-17 13:31
 * @email bnytezz@163.com
 */
public class RepostParameter<T> extends HashMap<String, T> {

    private Object[] parameters;

    public RepostParameter(Object[] parameters) {
        super();
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
