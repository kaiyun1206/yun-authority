package com.yun.authority.oauth2.model;

import cn.hutool.core.util.StrUtil;
import com.yun.authority.core.exception.AuthTokenException;

import java.io.Serializable;

/**
 * <p>
 *     请求授权参数的Model
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 20:41
 */
public class RequestAuthModel implements Serializable {
    private static final long serialVersionUID = -268988753081183166L;

    /**
     * 应用id
     */
    public String clientId;

    /**
     * 授权范围
     */
    public String scope;

    /**
     * 对应的账号id
     */
    public Object loginId;

    /**
     * 待重定向URL
     */
    public String redirectUri;

    /**
     * 授权类型, 非必填
     */
    public String responseType;

    /**
     * 状态标识, 可为null
     */
    public String state;


    /**
     * @return clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId 要设置的 clientId
     * @return 对象自身
     */
    public RequestAuthModel setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * @return scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope 要设置的 scope
     * @return 对象自身
     */
    public RequestAuthModel setScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * @return loginId
     */
    public Object getLoginId() {
        return loginId;
    }

    /**
     * @param loginId 要设置的 loginId
     * @return 对象自身
     */
    public RequestAuthModel setLoginId(Object loginId) {
        this.loginId = loginId;
        return this;
    }

    /**
     * @return redirectUri
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * @param redirectUri 要设置的 redirectUri
     * @return 对象自身
     */
    public RequestAuthModel setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    /**
     * @return responseType
     */
    public String getResponseType() {
        return responseType;
    }

    /**
     * @param responseType 要设置的 responseType
     * @return 对象自身
     */
    public RequestAuthModel setResponseType(String responseType) {
        this.responseType = responseType;
        return this;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state 要设置的 state
     * @return 对象自身
     */
    public RequestAuthModel setState(String state) {
        this.state = state;
        return this;
    }

    /**
     * 检查此Model参数是否有效
     * @return 对象自身
     */
    public RequestAuthModel checkModel() {
        if(StrUtil.isBlank(clientId)) {
            throw new AuthTokenException("无效client_id");
        }
        if(StrUtil.isBlank(scope)) {
            throw new AuthTokenException("无效scope");
        }
        if(StrUtil.isBlank(redirectUri)) {
            throw new AuthTokenException("无效redirect_uri");
        }
        if(StrUtil.isBlank(String.valueOf(loginId))) {
            throw new AuthTokenException("无效LoginId");
        }
        return this;
    }

}
