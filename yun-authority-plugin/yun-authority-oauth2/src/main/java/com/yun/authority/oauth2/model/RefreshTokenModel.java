package com.yun.authority.oauth2.model;

import java.io.Serializable;

/**
 * <p>
 *     Model: Refresh-Token
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 20:40
 */
public class RefreshTokenModel implements Serializable {
    private static final long serialVersionUID = 2456798506532457887L;

    /**
     * Refresh-Token 值
     */
    public String refreshToken;

    /**
     * Refresh-Token 到期时间
     */
    public long expiresTime;

    /**
     * 应用id
     */
    public String clientId;

    /**
     * 授权范围
     */
    public String scope;

    /**
     * 对应账号id
     */
    public Object loginId;

    /**
     * 对应账号id
     */
    public String openid;

    @Override
    public String toString() {
        return "RefreshTokenModel [refreshToken=" + refreshToken + ", expiresTime=" + expiresTime
                + ", clientId=" + clientId + ", scope=" + scope + ", loginId=" + loginId + ", openid=" + openid + "]";
    }

    /**
     * 获取：此 Refresh-Token 的剩余有效期（秒）
     * @return see note
     */
    public long getExpiresIn() {
        long s = (expiresTime - System.currentTimeMillis()) / 1000;
        return s < 1 ? -2 : s;
    }

}
