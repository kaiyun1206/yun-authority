package com.yun.authority.core.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     代表提供的 Id-Token 无效
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 16:14
 */
public class IdTokenInvalidException extends BaseException {
    private static final long serialVersionUID = 5054121331633264293L;

    /**
     * 一个异常：代表提供的 Id-Token 无效
     */
    public IdTokenInvalidException(String message) {
        super(message);
    }
}
