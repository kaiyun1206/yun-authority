package com.yun.authority.core.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     会话未能通过二级认证
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 16:54
 */
public class NotSafeException extends BaseException {
    private static final long serialVersionUID = -3876712198628139570L;

    /** 异常提示语 */
    public static final String BE_MESSAGE = "二级认证失败";

    /**
     * 一个异常：代表会话未通过二级认证
     */
    public NotSafeException() {
        super(BE_MESSAGE);
    }
}
