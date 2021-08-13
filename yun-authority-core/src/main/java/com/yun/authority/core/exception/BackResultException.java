package com.yun.authority.core.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     代表停止匹配，直接退出，向前端输出结果
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 16:09
 */
public class BackResultException extends BaseException {
    private static final long serialVersionUID = -8637008060134378863L;

    /**
     * 要输出的结果
     */
    public Object result;

    /**
     * 构造
     * @param result 要输出的结果
     */
    public BackResultException(Object result) {
        super(String.valueOf(result));
        this.result = result;
    }
}
