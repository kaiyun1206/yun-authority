package com.yun.authority.spring.oauth2;

import com.yun.authority.oauth2.OAuth2Manager;
import com.yun.authority.oauth2.config.OAuth2Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 *     注册 Token-OAuth2 所需要的Bean
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 11:42
 */
@ConditionalOnClass(OAuth2Manager.class)
public class OAuth2BeanRegister {

    /**
     * 获取OAuth2配置Bean
     * @return 配置对象
     */
    @Bean
    @ConfigurationProperties(prefix = "token.oauth2")
    public OAuth2Config getOAuth2Config() {
        return new OAuth2Config();
    }

}
