package com.yun.authority.core.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     Auth-Token框架内部逻辑发生错误抛出的异常 
 *     (自定义此异常方便开发者在做全局异常处理时分辨异常类型)
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 00:53
 */
public class AuthTokenException extends BaseException {
    private static final long serialVersionUID = 3561810016288361865L;

    /**
     * 构建一个异常
     *
     * @param message 异常描述信息
     */
    public AuthTokenException(String message) {
        super(message);
    }

    /**
     * 构建一个异常
     *
     * @param cause 异常对象
     */
    public AuthTokenException(Throwable cause) {
        super(cause);
    }

    /**
     * 构建一个异常
     *
     * @param message 异常信息
     * @param cause 异常对象
     */
    public AuthTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
