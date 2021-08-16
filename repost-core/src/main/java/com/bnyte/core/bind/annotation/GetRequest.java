package com.bnyte.core.bind.annotation;

import java.lang.annotation.*;

/**
 * @auther bnyte
 * @date 2021-08-16 16:27
 * @email bnytezz@163.com
 */
@Inherited
@Documented
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface GetRequest {



}
