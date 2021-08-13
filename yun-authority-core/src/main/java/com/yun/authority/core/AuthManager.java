package com.yun.authority.core;

import com.yun.authority.core.action.AuthTokeActionDefaultImpl;
import com.yun.authority.core.action.AuthTokenAction;
import com.yun.authority.core.auth.AuthInterface;
import com.yun.authority.core.auth.AuthInterfaceDefaultImpl;
import com.yun.authority.core.auth.AuthLogic;
import com.yun.authority.core.auth.AuthUtil;
import com.yun.authority.core.config.AuthTokenConfig;
import com.yun.authority.core.config.AuthTokenConfigFactory;
import com.yun.authority.core.context.AuthTokenContext;
import com.yun.authority.core.context.AuthTokenContextDefaultImpl;
import com.yun.authority.core.dao.AuthTokenDao;
import com.yun.authority.core.dao.AuthTokenDaoDefaultImpl;
import com.yun.authority.core.exception.AuthTokenException;
import com.yun.authority.core.interim.AuthInterimDefaultImpl;
import com.yun.authority.core.interim.AuthInterimInterface;
import com.yun.authority.core.listener.AuthTokenListener;
import com.yun.authority.core.listener.AuthTokenListenerDefaultImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     管理 权限Token 所有接口对象
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-08 22:17
 */
public class AuthManager {

    /**
     * 配置文件 Bean
     */
    public static AuthTokenConfig config;
    public static void setConfig(AuthTokenConfig config) {
        AuthManager.config = config;
        // 调用一次StpUtil中的方法，保证其可以尽早的初始化 StpLogic
        AuthUtil.getLoginType();
    }
    public static AuthTokenConfig getConfig() {
        if (config == null) {
            synchronized (AuthManager.class) {
                if (config == null) {
                    setConfig(AuthTokenConfigFactory.createConfig());
                }
            }
        }
        return config;
    }

    /**
     * 持久化 Bean
     */
    private static AuthTokenDao authTokenDao;
    public static void setAuthTokenDao(AuthTokenDao authTokenDao) {
        if((AuthManager.authTokenDao instanceof AuthTokenDaoDefaultImpl)) {
            ((AuthTokenDaoDefaultImpl)AuthManager.authTokenDao).endRefreshThread();
        }
        AuthManager.authTokenDao = authTokenDao;
    }
    public static AuthTokenDao getAuthTokenDao() {
        if (authTokenDao == null) {
            synchronized (AuthManager.class) {
                if (authTokenDao == null) {
                    setAuthTokenDao(new AuthTokenDaoDefaultImpl());
                }
            }
        }
        return authTokenDao;
    }

    /**
     * 权限认证 Bean
     */
    private static AuthInterface authInterface;
    public static void setAuthInterface(AuthInterface authInterface) {
        AuthManager.authInterface = authInterface;
    }
    public static AuthInterface getAuthInterface() {
        if (authInterface == null) {
            synchronized (AuthManager.class) {
                if (authInterface == null) {
                    setAuthInterface(new AuthInterfaceDefaultImpl());
                }
            }
        }
        return authInterface;
    }

    /**
     * 框架行为 Bean
     */
    private static AuthTokenAction authTokenAction;
    public static void setAuthTokenAction(AuthTokenAction authTokenAction) {
        AuthManager.authTokenAction = authTokenAction;
    }
    public static AuthTokenAction getAuthTokenAction() {
        if (authTokenAction == null) {
            synchronized (AuthManager.class) {
                if (authTokenAction == null) {
                    setAuthTokenAction(new AuthTokeActionDefaultImpl());
                }
            }
        }
        return authTokenAction;
    }

    /**
     * 容器操作 Bean
     */
    private static AuthTokenContext authTokenContext;
    public static void setAuthTokenContext(AuthTokenContext authTokenContext) {
        AuthManager.authTokenContext = authTokenContext;
    }
    public static AuthTokenContext getAuthTokenContext() {
        if (authTokenContext == null) {
            synchronized (AuthManager.class) {
                if (authTokenContext == null) {
                    setAuthTokenContext(new AuthTokenContextDefaultImpl());
                }
            }
        }
        return authTokenContext;
    }

    /**
     * 侦听器 Bean
     */
    private static AuthTokenListener authTokenListener;
    public static void setAuthTokenListener(AuthTokenListener authTokenListener) {
        AuthManager.authTokenListener = authTokenListener;
    }
    public static AuthTokenListener getAuthTokenListener() {
        if (authTokenListener == null) {
            synchronized (AuthManager.class) {
                if (authTokenListener == null) {
                    setAuthTokenListener(new AuthTokenListenerDefaultImpl());
                }
            }
        }
        return authTokenListener;
    }

    /**
     * 临时令牌验证模块 Bean
     */
    private static AuthInterimInterface authInterim;
    public static void setAuthInterim(AuthInterimInterface authInterim) {
        AuthManager.authInterim = authInterim;
    }
    public static AuthInterimInterface getAuthInterim() {
        if (authInterim == null) {
            synchronized (AuthManager.class) {
                if (authInterim == null) {
                    setAuthInterim(new AuthInterimDefaultImpl());
                }
            }
        }
        return authInterim;
    }

    /**
     * AuthLogic集合, 记录框架所有成功初始化的AuthLogic
     */
    public static Map<String, AuthLogic> authLogicMap = new HashMap<String, AuthLogic>();

    /**
     * 向集合中 put 一个 AuthLogic
     * @param authLogic authLogic
     */
    public static void putAuthLogic(AuthLogic authLogic) {
        authLogicMap.put(authLogic.getLoginType(), authLogic);
    }

    /**
     * 根据 LoginType 获取对应的StpLogic，如果不存在则抛出异常
     * @param loginType 对应的账号类型
     * @return 对应的StpLogic
     */
    public static AuthLogic getAuthLogic(String loginType) {
        // 如果type为空则返回框架内置的
        if(loginType == null || loginType.isEmpty()) {
            return AuthUtil.authLogic;
        }

        // 从SaManager中获取
        AuthLogic authLogic = authLogicMap.get(loginType);
        if(authLogic == null) {
            /*
             * 此时有两种情况会造成 StpLogic == null
             * 1. loginType拼写错误，请改正 （建议使用常量）
             * 2. 自定义StpUtil尚未初始化（静态类中的属性至少一次调用后才会初始化），解决方法两种
             * 		(1) 从main方法里调用一次
             * 		(2) 在自定义StpUtil类加上类似 @Component 的注解让容器启动时扫描到自动初始化
             */
            throw new AuthTokenException("未能获取对应StpLogic，type="+ loginType);
        }

        // 返回
        return authLogic;
    }
}
