package com.bnyte.core.bind.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *     代理缓存，在接口的代理对象被完成初始化之前，会通过判断当前注解中是否包含了当前的代理缓存，
 *     如果不包含则会去全局配置文件中读取是否需要开启全局缓存如果都没有开启则将说明需要取消缓存，
 *     不为其开启缓存
 * </p>
 * @auther bnyte
 * @date 2021-08-19 18:00
 * @email bnytezz@163.com
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface ProxyCache {
    /**
     * 注解默认值，是否需要开启缓存，默认会以当前注解为准，如：全局配置类配置了开启缓存，但是动态代理对象被当前注解标识了不需要缓存
     * 那么此时即使在application.yaml中配置了全局缓存但是此时依然不会使用配置文件中的全局配置
     * @return 返回当前是否开启缓存，默认为false不开启缓存
     */
    boolean value() default false;

    /**
     * 该注解与value注解相同，但是需要注意的是当前注解的优先级要高于value，即使是当前注解配置false但是value同时制定了true，那么此时依然不会使用缓存
     * @return 返回是否开启缓存，默认为false不会开启缓存
     */
    boolean enableCache() default false;

    /**
     * 指定当前注解标记的接口或方法的id，如果为空则默认使用接口名首字母小写其他不变，二如果标记方法但是没有指定则会使用当前方法名作为id
     * 但是因为方法会存在重名的可能，如果重名必须手动指定否则缓存就无法起到作用。
     * @return 返回当前注解标记的类或者方法指定的id，默认清空下标记了接口则说明当前接口使用类名首字母小写其他不变的方式作为id，而方法则是直接使用方法名
     */
    String id() default "";

}
