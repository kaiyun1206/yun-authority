package com.yun.authority.spring.oauth2;

import com.yun.authority.oauth2.OAuth2Manager;
import com.yun.authority.oauth2.config.OAuth2Config;
import com.yun.authority.oauth2.logic.OAuth2Template;
import com.yun.authority.oauth2.logic.OAuth2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * <p>
 *     Token-OAuth2 所需要的Bean
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 11:39
 */
@ConditionalOnClass(OAuth2Manager.class)
public class OAuth2BeanInject {

    /**
     * 注入OAuth2配置Bean
     *
     * @param oAuth2Config 配置对象
     */
    @Autowired(required = false)
    public void setSaOAuth2Config(OAuth2Config oAuth2Config) {
        OAuth2Manager.setConfig(oAuth2Config);
    }

    /**
     * 注入代码模板Bean
     *
     * @param oAuth2Template 代码模板Bean
     */
    @Autowired(required = false)
    public void setOAuth2Interface(OAuth2Template oAuth2Template) {
        OAuth2Util.oAuth2Template = oAuth2Template;
    }
}
