package com.bnyte.core.expand;

/**
 * @auther bnyte
 * @date 2021-08-16 17:34
 * @email bnytezz@163.com
 */
public class KeyValue {

    /**
     * 当前key的类型，该类型指向的是key的字节码对象
     */
    private Class<?> valueType = null;

    /**
     * 当前key的id，在当前URL请求中唯一不可重复，keyId默认就是当前请求参数的key，如：[name=2] keyId=name
     */
    private String keyId = null;

    /**
     * 当前key的值
     */
    private Object keyVal = null;

    public KeyValue(Class<?> keyType, String keyId, Object keyVal) {
        this.valueType = keyType;
        this.keyId = keyId;
        this.keyVal = keyVal;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    public void setValueType(Class<?> valueType) {
        this.valueType = valueType;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Object getKeyVal() {
        return keyVal;
    }

    public void setKeyVal(Object keyVal) {
        this.keyVal = keyVal;
    }

    public String getUrlParameter() {
        String urlParameter = this.keyId + "=" + this.keyVal;
        return urlParameter;
    }

}
