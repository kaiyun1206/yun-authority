package com.yun.authority.core.annotation;

/**
 * <p>
 *     注解鉴权的验证模式
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 14:35
 */
public enum CheckMode {

    /**
     * 必须具有所有的元素
     */
    AND,

    /**
     * 只需具有其中一个元素
     */
    OR

}
