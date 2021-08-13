package com.yun.authority.spring;

import com.yun.authority.core.config.AuthTokenConfig;
import com.yun.authority.core.context.AuthTokenContext;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *     注册Auth-Token所需要的Bean
 *     Bean 的注册与注入应该分开在两个文件中，否则在某些场景下会造成循环依赖
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 14:25
 */
public class AuthBeanRegister {

    /**
     * 获取配置Bean
     *
     * @return 配置对象
     */
    @Bean
    @ConfigurationProperties(prefix = "auth-token")
    public AuthTokenConfig getAuthTokenConfig() {
        return new AuthTokenConfig();
    }

    /**
     * 获取容器交互Bean (Spring版)
     *
     * @return 容器交互Bean (Spring版)
     */
    @Bean
    public AuthTokenContext getAuthTokenContext() {
        return new AuthTokenContextForSpring();
    }

}
