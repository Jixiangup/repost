package com.bnyte.core.cache;

import com.bnyte.core.app.RepostInterface;
import com.bnyte.core.exception.RepostRuntimeException;
import com.bnyte.core.util.StringUtils;

import java.util.HashMap;

/**
 * @auther bnyte
 * @date 2021-08-17 13:16
 * @email bnytezz@163.com
 */
public class RepostCache extends HashMap<String, RepostInterface> {

    public RepostCache() {
        super();
    }


    /**
     * 如果能够get到说明一定在接口池里边，换句话说，在接口池一定就是开启了接口池，所以这里不在多做判断
     * @param interfaceId 接口ID
     * @return 返回接口对象
     */
    public RepostInterface get(String interfaceId) {
        if (!StringUtils.hasText(interfaceId)) {
            throw new RepostRuntimeException("interface id cannot be empty, please check the incoming [interfaceId]");
        }
        return this.get((Object) interfaceId);
    }

    @Override
    public RepostInterface get(Object key) {
        return super.get(key);
    }

    @Override
    public RepostInterface put(String key, RepostInterface value) {
        return super.put(key, value);
    }
}
