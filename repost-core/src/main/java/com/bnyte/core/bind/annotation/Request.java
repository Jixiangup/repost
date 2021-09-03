package com.bnyte.core.bind.annotation;

import com.bnyte.core.bind.enums.RequestMethod;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Request {

    /**
     * value默认指请求地址, 但是url优先级会高于value, 当url为空是会使用value
     */
    String value() default "";

    /**
     * 请求地址
     */
    String url() default "";

    /**
     * 请求方式
     */
    RequestMethod[] methods() default {RequestMethod.GET};

    /**
     * 是否开启缓存
     */
    boolean enableCache() default false;

}
