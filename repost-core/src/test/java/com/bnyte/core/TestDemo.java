package com.bnyte.core;

import com.bnyte.core.cache.InterfaceCache;
import com.bnyte.core.context.RepostInterface;
import org.junit.Test;

/**
 * @auther bnyte
 * @date 2021-08-17 13:20
 * @email bnytezz@163.com
 */
public class TestDemo {

    @Test
    public void test() throws Exception {
        InterfaceCache<String, RepostInterface> interfaceCache = new InterfaceCache<>();
        interfaceCache.put("testController", new RepostInterface());
        System.out.println(interfaceCache.get(""));
        System.out.println(interfaceCache.size());
        System.out.println(interfaceCache.isEmpty());
    }

}
