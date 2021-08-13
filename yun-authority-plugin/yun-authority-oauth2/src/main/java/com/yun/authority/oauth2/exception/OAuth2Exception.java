package com.yun.authority.oauth2.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     代表OAuth2认证流程错误
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 20:20
 */
public class OAuth2Exception extends BaseException {
    private static final long serialVersionUID = 1248069300566213101L;

    /**
     * 一个异常：代表OAuth2认证流程错误
     * @param message 异常描述
     */
    public OAuth2Exception(String message) {
        super(message);
    }

    /**
     * 如果flag==true，则抛出message异常
     * @param flag 标记
     * @param message 异常信息
     */
    public static void throwBy(boolean flag, String message) {
        if(flag) {
            throw new OAuth2Exception(message);
        }
    }
}
