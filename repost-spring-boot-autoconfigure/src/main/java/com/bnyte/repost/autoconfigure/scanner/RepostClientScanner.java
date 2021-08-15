package com.bnyte.repost.autoconfigure.scanner;

import com.bnyte.repost.autoconfigure.config.RepostProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Arrays;
import java.util.Set;

public class RepostClientScanner extends ClassPathBeanDefinitionScanner {

    private RepostProperties repostProperties;

    public RepostClientScanner(BeanDefinitionRegistry registry, RepostProperties repostProperties) {
        super(registry, false);
        this.repostProperties = repostProperties;
        registerFilters(repostProperties);
    }

    /**
     * 重写过滤条件
     */
    public void registerFilters(RepostProperties repostProperties) {
        // 判断用户是否配置需要全局扫描
        if (repostProperties.isAllInterfaces()) {
            addIncludeFilter(new RepostClientIncludeFilter());
        } else {
            addExcludeFilter(new RepostClientExcludeFilter());
        }
    }


    /**
     * 重写扫描逻辑
     * @param basePackages 请求接口类所在的包路径，只能是第一层的包，不包含子包
     * @return BeanDefinitionHolder实例集合
     */
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            logger.warn("[Forest] No Forest client is found in package '" + Arrays.toString(basePackages) + "'.");
        }
        processBeanDefinitions(beanDefinitions);
        return beanDefinitions;
    }


    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();

            if (logger.isDebugEnabled()) {
                logger.debug("[Repost] Creating Forest Client Bean with name '" + holder.getBeanName()
                        + "' and Proxy of '" + definition.getBeanClassName() + "' client interface");
            }

            String beanClassName = definition.getBeanClassName();
            // TODO 获取到类名
            logger.info("[Forest] Created Forest Client Bean with name '" + holder.getBeanName()
                    + "' and Proxy of '" + beanClassName + "' client interface");

        }
    }

}
