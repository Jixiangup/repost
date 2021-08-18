package com.bnyte.core.handler;

import com.bnyte.core.bind.annotation.Request;
import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.context.RepostInterface;
import com.bnyte.core.context.RepostMethod;
import com.bnyte.core.context.RepostParameter;
import com.bnyte.core.context.RepostRequest;
import com.bnyte.core.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @auther bnyte
 * @date 2021-08-16 18:18
 * @email bnytezz@163.com
 */
public class RepostRequestHandler extends AbstractRepostRequest {

    private RepostInterface repostInterface;
    private RepostMethod repostMethod;
    private RepostParameter<Object> repostParameter;
    private static RepostConfig config = RepostRequestHandler.getConfig();

    public RepostRequestHandler(Method method, Class<?> interfaceType, Object[] parameters) {
        super(method, interfaceType, parameters);
        // 初始化当前请求之前，初始化三大对象 接口 方法 属性
        initRepostRequestBefore();

    }

    @Override
    public void initRepostRequestBefore() {
        Class<?> interFaceType = getInterFaceType();
        // 初始化当前接口
        RepostInterface repostInterface = initRepostInterface(interFaceType);
    }

    // 初始化接口
    private RepostInterface initRepostInterface (Class<?> interfaceType) {
        // 当前接口id，这是唯一标识
        String interfaceId = ClassUtils.getBeanName(interfaceType);
        Request request = interfaceType.getAnnotation(Request.class);
        boolean enableCache = request.enableCache();
        RepostInterface cache = null;
        // 判断当前接口中是否开启缓存
        if (!enableCache) {
            // 如果本也没有开启则都配置文件中是否配置了开启
            enableCache = config.isEnableCache();
        }
        /**
         * 如果目前全局RepostRequest对象中的接口池为空那么则说明当前是所有接口第一次被调用，也就是说在这里如果调用接口池的相关方法会发生空指针的bug
         * 所以此时我们会读出当前类中的注解来判断是否自定义需要开启缓存，如果不需要，那Repost会去全局config拿数据判断是否配置了开启缓存，如果有配置
         * 那次是就会判断，如果依然没有开启则直接跳过为当前接口缓存初始化减少内存占用
         * 当然他们的优先级顺序分别是 @Repost(enable=true)其次是配置文件中的bnyte.repost.enable=true
         */
        if (RepostRequest.getInterfaceCache() == null) {
            // 开启了缓存为RepostRequest的全局缓存开启
            if (enableCache) {
                // 说明现在是第一次进行接口初始化，所以需要对接口缓存池初始化
                RepostRequest.setInterfaceCache(new InterfaceCache<>());
            }
        } else {
            if (enableCache) {
                // 从缓存获取
                cache = interfaceCache.get(interfaceId);
            }
        }

        // 开启缓存的清空
        if (enableCache) {
            /*
                如果开启了缓存优先判断当前接口缓存池中是否为空，如果为空则说明是第一次解析请求对象，所以需要为接口缓存池初始化避免浪费内存
             */
            if (RepostRequest.getInterfaceCache() == null) {
                RepostRequest.setInterfaceCache(new InterfaceCache<>());
            }

        }
        // 没有开启缓存
        else {

        }

        RepostMethod repostMethod = null;

        /*
            缓存中查到了数据，直接初始化方法不在乎接口上的一些东西
         */
        if (cache != null) {
            repostMethod = RepostMethod.initRequestMethod(this.method, cache);
        }
        // 当前接口上的所有注解
        Annotation[] annotations = interfaceType.getAnnotations();

        // 初始化当前接口执行的方法
        repostMethod = RepostMethod.initRequestMethod(this.method, cache);

        if (cache == null) {
            cache =
                    new RepostInterface(interfaceId, interfaceType, annotations, repostMethod, enableCache);
        }

        this.repostInterface = cache;

        return this.repostInterface;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void initAfter() {
        super.initAfter();
    }

    public Class<?> getInterFaceType() {
        return this.interfaceType;
    }



    public RepostInterface getRepostInterface() {
        return repostInterface;
    }

    public void setRepostInterface(RepostInterface repostInterface) {
        this.repostInterface = repostInterface;
    }

    public RepostMethod getRepostMethod() {
        return repostMethod;
    }

    public void setRepostMethod(RepostMethod repostMethod) {
        this.repostMethod = repostMethod;
    }

    public RepostParameter<Object> getRepostParameter() {
        return repostParameter;
    }

    public void setRepostParameter(RepostParameter<Object> repostParameter) {
        this.repostParameter = repostParameter;
    }

    public static RepostConfig getConfig() {
        return RepostConfig.getRepostConfig();
    }

    /**
     * 将当前接口添加到接口缓存池中去
     * @return true说明添加成功，false说明添加失败
     */
    public void addInterfaceToCache(String interfaceId, RepostInterface repostInterface) {
        InterfaceCache<String, RepostInterface> cache = RepostRequest.getInterfaceCache();
        // 判断当前接口缓存池中是否为空，如果为空则说明当前接口一定
        if (cache.isEmpty()) {
            // 为空直接将当前接口添加进去
            cache.put(interfaceId, repostInterface);
        }
        else {
            // 去线程池里面拿，如果已经有了，那就做equal，如果equal则不添加，如果不equal则覆盖
            RepostInterface anInterface = cache.get(interfaceId);
            if (cache.containsKey(interfaceId)) {
                // 已经有了 拿值做equal
                if (!anInterface.equals(repostInterface)) {
                    cache.put(interfaceId, repostInterface);
                }
            }
        }
    }
}
