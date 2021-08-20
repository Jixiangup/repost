package com.bnyte.core.handler;

import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.config.RepostConfig;
import com.bnyte.core.context.RepostInterface;
import com.bnyte.core.context.RepostMethod;
import com.bnyte.core.context.RepostParameter;

import java.lang.reflect.Method;

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
        RepostInterface repostInterface = RepostInterface.initRepostInterface(interFaceType, this.method, this.parameters);
        this.repostInterface = repostInterface;
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
        InterfaceCache<String, RepostInterface> cache = InterfaceCache.getInterfaceCache();
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
