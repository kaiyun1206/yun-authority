package com.yun.authority.core.auth;

import com.yun.authority.core.AuthManager;
import com.yun.authority.core.config.AuthTokenConfig;
import com.yun.authority.core.constants.AuthTokenConstants;
import com.yun.authority.core.dao.AuthTokenDao;

/**
 * <p>
 *     调用 `AuthUtil.login()` 时的 [配置参数 Model ]
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 15:21
 */
public class AuthLoginModel {

    /**
     * 此次登录的客户端设备标识 
     */
    public String device;

    /**
     * 是否为持久Cookie（临时Cookie在浏览器关闭时会自动删除，持久Cookie在重新打开后依然存在）
     */
    public Boolean isLastingCookie;

    /**
     * 指定此次登录token的有效期, 单位:秒 （如未指定，自动取全局配置的timeout值）
     */
    public Long timeout;


    /**
     * @return 参考 {@link #device}
     */
    public String getDevice() {
        return device;
    }

    /**
     * @param device 参考 {@link #device}
     * @return 对象自身
     */
    public AuthLoginModel setDevice(String device) {
        this.device = device;
        return this;
    }

    /**
     * @return 参考 {@link #isLastingCookie}
     */
    public Boolean getIsLastingCookie() {
        return isLastingCookie;
    }

    /**
     * @param isLastingCookie 参考 {@link #isLastingCookie}
     * @return 对象自身
     */
    public AuthLoginModel setIsLastingCookie(Boolean isLastingCookie) {
        this.isLastingCookie = isLastingCookie;
        return this;
    }

    /**
     * @return 参考 {@link #timeout}
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * @param timeout 参考 {@link #timeout}
     * @return 对象自身
     */
    public AuthLoginModel setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }


    /**
     * @return Cookie时长
     */
    public int getCookieTimeout() {
        if(isLastingCookie == false) {
            return -1;
        }
        if(timeout == AuthTokenDao.NEVER_EXPIRE) {
            return Integer.MAX_VALUE;
        }
        return (int)(long)timeout;
    }

    /**
     * 构建对象，初始化默认值 
     * @return 对象自身
     */
    public AuthLoginModel build() {
        return build(AuthManager.getConfig());
    }

    /**
     * 构建对象，初始化默认值 
     * @param config 配置对象 
     * @return 对象自身
     */
    public AuthLoginModel build(AuthTokenConfig config) {
        if(device == null) {
            device = AuthTokenConstants.DEFAULT_LOGIN_DEVICE;
        }
        if(isLastingCookie == null) {
            isLastingCookie = true;
        }
        if(timeout == null) {
            timeout = config.getTimeout();
        }
        return this;
    }

    /**
     * 静态方法获取一个 AuthLoginModel 对象
     * @return AuthLoginModel 对象 
     */
    public static AuthLoginModel create() {
        return new AuthLoginModel();
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "AuthLoginModel [device=" + device + ", isLastingCookie=" + isLastingCookie + ", timeout=" + timeout + "]";
    }
}
