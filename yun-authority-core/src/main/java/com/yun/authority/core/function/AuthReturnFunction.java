package com.yun.authority.core.function;

/**
 * <p>
 *     设定一个函数，并返回一个值，方便在Lambda表达式下的函数式编程
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:47
 */
@FunctionalInterface
public interface AuthReturnFunction {

    /**
     * 执行的方法
     * @return 返回值
     */
    Object run();
}
