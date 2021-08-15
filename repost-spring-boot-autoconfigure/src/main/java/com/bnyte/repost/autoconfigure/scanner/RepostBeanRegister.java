package com.bnyte.repost.autoconfigure.scanner;

import com.bnyte.repost.autoconfigure.config.RepostProperties;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class RepostBeanRegister implements ResourceLoaderAware, BeanPostProcessor {

    private ResourceLoader resourceLoader;

    private final ConfigurableApplicationContext applicationContext;

    private RepostProperties repostProperties;

    public RepostBeanRegister(ConfigurableApplicationContext applicationContext, RepostProperties repostProperties) {
        this.applicationContext = applicationContext;
        this.repostProperties = repostProperties;
    }

    public RepostClientScanner registerScanner() {
        List<String> basePackages = RepostScanRegister.basePackages;
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getBeanFactory();

        RepostClientScanner scanner = new RepostClientScanner(registry, repostProperties);
        // this check is needed in Spring 3.1
        if (resourceLoader != null) {
            scanner.setResourceLoader(resourceLoader);
        }

        if (CollectionUtils.isEmpty(basePackages)) {
            return scanner;
        }

        scanner.doScan(StringUtils.toStringArray(basePackages));

        return scanner;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
