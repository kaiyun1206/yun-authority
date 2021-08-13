package com.yun.authority.core.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     代表停止路由匹配
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 16:08
 */
public class StopMatchException extends BaseException {
    private static final long serialVersionUID = 4963351190145883779L;

    /**
     * 构造
     */
    public StopMatchException() {
        super("stop match");
    }
}
