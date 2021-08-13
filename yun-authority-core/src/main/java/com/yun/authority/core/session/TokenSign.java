package com.yun.authority.core.session;

import java.io.Serializable;

/**
 * <p>
 * Token签名
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 16:41
 */
public class TokenSign implements Serializable {
    private static final long serialVersionUID = 8876514416453852809L;

    /**
     * token值
     */
    private String value;

    /**
     * 所在设备标识
     */
    private String device;

    /**
     * 构建一个
     */
    public TokenSign() {
    }

    /**
     * 构建一个
     *
     * @param value  token值
     * @param device 所在设备标识
     */
    public TokenSign(String value, String device) {
        this.value = value;
        this.device = device;
    }

    /**
     * @return token value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return token登录设备
     */
    public String getDevice() {
        return device;
    }

    @Override
    public String toString() {
        return "TokenSign [value=" + value + ", device=" + device + "]";
    }
}
