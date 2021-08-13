package com.yun.authority.oauth2.model;

import java.io.Serializable;

/**
 * <p>
 *     Client应用信息 Model
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 20:46
 */
public class ClientModel implements Serializable {
    private static final long serialVersionUID = 3622175510537967528L;

    /**
     * 应用id
     */
    public String clientId;

    /**
     * 应用秘钥
     */
    public String clientSecret;

    /**
     * 应用签约的所有权限, 多个用逗号隔开
     */
    public String contractScope;

    /**
     * 应用允许授权的所有URL, 多个用逗号隔开
     */
    public String allowUrl;

    public ClientModel() {

    }
    public ClientModel(String clientId, String clientSecret, String contractScope, String allowUrl) {
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.contractScope = contractScope;
        this.allowUrl = allowUrl;
    }

    /**
     * @return 应用id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId 应用id
     * @return 对象自身
     */
    public ClientModel setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * @return 应用秘钥
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret 应用秘钥
     * @return 对象自身
     */
    public ClientModel setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    /**
     * @return 应用签约的所有权限, 多个用逗号隔开
     */
    public String getContractScope() {
        return contractScope;
    }

    /**
     * @param contractScope 应用签约的所有权限, 多个用逗号隔开
     * @return 对象自身
     */
    public ClientModel setContractScope(String contractScope) {
        this.contractScope = contractScope;
        return this;
    }

    /**
     * @return 应用允许授权的所有URL, 多个用逗号隔开
     */
    public String getAllowUrl() {
        return allowUrl;
    }

    /**
     * @param allowUrl 应用允许授权的所有URL, 多个用逗号隔开
     * @return 对象自身
     */
    public ClientModel setAllowUrl(String allowUrl) {
        this.allowUrl = allowUrl;
        return this;
    }

    @Override
    public String toString() {
        return "ClientModel [clientId=" + clientId + ", clientSecret=" + clientSecret + ", contractScope="
                + contractScope + ", allowUrl=" + allowUrl + "]";
    }

}
