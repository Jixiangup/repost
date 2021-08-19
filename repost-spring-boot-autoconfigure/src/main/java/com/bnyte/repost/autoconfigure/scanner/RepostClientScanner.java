package com.bnyte.repost.autoconfigure.scanner;

import com.bnyte.core.bind.annotation.RepostClient;
import com.bnyte.repost.autoconfigure.config.RepostProperties;
import com.bnyte.repost.autoconfigure.proxy.RepostFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.*;

public class RepostClientScanner extends ClassPathBeanDefinitionScanner {

    private RepostProperties repostProperties;
    private BeanDefinitionRegistry registry;

    public RepostClientScanner(BeanDefinitionRegistry registry, RepostProperties repostProperties) {
        super(registry, false);
        if (this.registry == null) {
            setRegistry(registry);
        }
        this.repostProperties = repostProperties;
        registerFilters(repostProperties);
    }

    public void setRegistry(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 重写过滤条件
     */
    public void registerFilters(RepostProperties repostProperties) {
        // 判断用户是否配置需要全局扫描
        if (repostProperties.isAllInterfaces()) {
            addIncludeFilter(new RepostClientIncludeFilter());
            addIncludeFilter(new AnnotationTypeFilter(RepostClient.class));
        }
        addExcludeFilter(new RepostClientExcludeFilter());
    }


    /**
     * 重写扫描逻辑
     * @param basePackages 请求接口类所在的包路径，只能是第一层的包，不包含子包
     * @return BeanDefinitionHolder实例集合
     */
    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 扫描完成之后我们获得了所有需要注册的类
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        for (BeanDefinitionHolder beanDefinition : beanDefinitions) {

            registerProxy(registry, beanDefinition);

        }

        if (beanDefinitions.isEmpty()) {
            logger.warn("[Repost] No Forest client is found in package '" + Arrays.toString(basePackages) + "'.");
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

            logger.info("[Repost] Created Repost Client Bean with name '" + holder.getBeanName()
                    + "' and Proxy of '" + beanClassName + "' client interface");

        }
    }

    public void registerProxy(BeanDefinitionRegistry registry, BeanDefinitionHolder beanDefinitionHolder) {
        String beanName = beanDefinitionHolder.getBeanName();
        String beanClassName = beanDefinitionHolder.getBeanDefinition().getBeanClassName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        beanDefinition.setBeanClass(RepostFactoryBean.class);
        // 使用RepostFactoryBean类的构造器进行实例化
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazz);
        // 将对象注入到IOC容器当中
        registry.registerBeanDefinition(beanName, beanDefinition);
    }


    /**
     * 这一行必须添加否则接口会被过滤掉
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 如果为真则会扫描所有，包括类，但是类会抛出异常，也就是动态代理失败
//        if (repostProperties.isAllInterfaces()) {
//            return true;
//        } else {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
//        }
    }
}
