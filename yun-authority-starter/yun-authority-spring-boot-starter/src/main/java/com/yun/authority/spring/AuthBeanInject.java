package com.yun.authority.spring;

import com.yun.authority.core.AuthManager;
import com.yun.authority.core.action.AuthTokenAction;
import com.yun.authority.core.auth.AuthInterface;
import com.yun.authority.core.config.AuthTokenConfig;
import com.yun.authority.core.context.AuthTokenContext;
import com.yun.authority.core.dao.AuthTokenDao;
import com.yun.authority.core.id.AuthIdTemplate;
import com.yun.authority.core.id.AuthIdUtil;
import com.yun.authority.core.interim.AuthInterimInterface;
import com.yun.authority.core.listener.AuthTokenListener;
import com.yun.authority.core.sso.AuthSSOTemplate;
import com.yun.authority.core.sso.AuthSSOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.PathMatcher;

/**
 * <p>
 *     注入Auth-Token所需要的Bean
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 13:57
 */
public class AuthBeanInject {

    /**
     * 注入配置Bean
     *
     * @param authTokenConfig 配置对象
     */
    @Autowired(required = false)
    public void setConfig(AuthTokenConfig authTokenConfig) {
        AuthManager.setConfig(authTokenConfig);
    }

    /**
     * 注入持久化Bean
     *
     * @param authTokenDao AuthTokenDao对象
     */
    @Autowired(required = false)
    public void setAuthTokenDao(AuthTokenDao authTokenDao) {
        AuthManager.setAuthTokenDao(authTokenDao);
    }

    /**
     * 注入权限认证Bean
     *
     * @param authInterface AuthInterface对象
     */
    @Autowired(required = false)
    public void setStpInterface(AuthInterface authInterface) {
        AuthManager.setAuthInterface(authInterface);
    }

    /**
     * 注入框架行为Bean
     *
     * @param authTokenAction AuthTokenAction对象
     */
    @Autowired(required = false)
    public void setAuthTokenAction(AuthTokenAction authTokenAction) {
        AuthManager.setAuthTokenAction(authTokenAction);
    }

    /**
     * 注入容器交互Bean
     *
     * @param authTokenContext AuthTokenContext对象
     */
    @Autowired(required = false)
    public void setAuthTokenContext(AuthTokenContext authTokenContext) {
        AuthManager.setAuthTokenContext(authTokenContext);
    }

    /**
     * 注入侦听器Bean
     *
     * @param authTokenListener AuthTokenListener对象
     */
    @Autowired(required = false)
    public void setAuthTokenListener(AuthTokenListener authTokenListener) {
        AuthManager.setAuthTokenListener(authTokenListener);
    }

    /**
     * 注入临时令牌验证模块 Bean
     *
     * @param authInterim AuthInterimInterface对象
     */
    @Autowired(required = false)
    public void setAuthInterim(AuthInterimInterface authInterim) {
        AuthManager.setAuthInterim(authInterim);
    }

    /**
     * 注入 Auth-Id-Token 模块 Bean
     *
     * @param authIdTemplate AuthIdTemplate对象
     */
    @Autowired(required = false)
    public void setAuthIdTemplate(AuthIdTemplate authIdTemplate) {
        AuthIdUtil.authIdTemplate = authIdTemplate;
    }

    /**
     * 注入 Yun-Auth-SSO 单点登录模块 Bean
     *
     * @param authSSOTemplate AuthSSOTemplate对象
     */
    @Autowired(required = false)
    public void setAuthSSOTemplate(AuthSSOTemplate authSSOTemplate) {
        AuthSSOUtil.authSSOTemplate = authSSOTemplate;
    }

    /**
     * 利用自动注入特性，获取Spring框架内部使用的路由匹配器
     *
     * @param pathMatcher 要设置的 pathMatcher
     */
    @Autowired(required = false)
    @Qualifier("mvcPathMatcher")
    public void setPathMatcher(PathMatcher pathMatcher) {
        AuthPathMatcherHolder.setPathMatcher(pathMatcher);
    }

}
