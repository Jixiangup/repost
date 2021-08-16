package com.bnyte.repost.autoconfigure.auto;

import com.bnyte.core.config.RepostConfig;
import com.bnyte.repost.autoconfigure.config.RepostProperties;
import com.bnyte.repost.autoconfigure.scanner.RepostBeanRegister;
import com.bnyte.repost.autoconfigure.scanner.RepostScanRegister;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties(RepostProperties.class)
@Configuration
//@Import({RepostScanRegister.class})
public class RepostAutoConfiguration {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    RepostProperties repostProperties;

    @Bean
    public RepostBeanRegister repostBeanRegister() {
        RepostBeanRegister repostBeanRegister = new RepostBeanRegister(applicationContext, repostProperties);
        repostBeanRegister.registerScanner();
        // 初始化配置类
        RepostConfig repostConfig = initRepostConfig(repostProperties);
        RepostConfig.setRepostConfig(repostConfig);
        return repostBeanRegister;
    }

    /**
     * 初始化配置类
     */
    public RepostConfig initRepostConfig(RepostProperties repostProperties) {
        RepostConfig config = new RepostConfig();
        BeanUtils.copyProperties(repostProperties, config);
        return config;
    }

}
