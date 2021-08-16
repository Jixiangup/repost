package com.bnyte.core.definition;

import com.bnyte.core.expand.KeyValue;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther bnyte
 * @date 2021-08-16 17:12
 * @email bnytezz@163.com
 */
public interface GetDefinition extends RequestDefinition {

    /**
     * 当前请求参数中的key集合，会按照添加顺序排序
     */
//    List<Key> keys = new LinkedList<>();

    /**
     * 当前请求参数中的value集合，会按照添加顺序排序
     */
//    List<Value> values = new LinkedList<>();

    /**
     * 当前请求参数当中的key&value集合，每个KeyValue对象都是一个独立的key value，
     * 如 [127.0.0.1:8080?name=bnyte&age=18]，此时的key表示单独的 [name] 这里你可以获取到属于他的独特信息
     * 而此时的value表示着${name}对应的value [bnyte] 这里你同样可以获取到属于他的独特信息
     */
    List<KeyValue> keyValue = new LinkedList<>();



}
