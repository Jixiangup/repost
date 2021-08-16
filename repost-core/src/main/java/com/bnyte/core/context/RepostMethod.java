package com.bnyte.core.context;

import java.lang.reflect.Method;

/**
 * @author bnyte
 * @version 1.0.0
 * @email bnytezz@163.com
 * @date 2021-08-17 00:41
 */
public class RepostMethod {
    private Method[] interfaceMethods;
    private int count;

    public RepostMethod() {
    }

    public RepostMethod(Method[] interfaceMethods, int count) {
        this.interfaceMethods = interfaceMethods;
        this.count = count;
    }

    public Method[] getInterfaceMethods() {
        return interfaceMethods;
    }

    public void setInterfaceMethods(Method[] interfaceMethods) {
        this.interfaceMethods = interfaceMethods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
