package com.yun.authority.core.filter;

/**
 * <p>
 *     Token全局过滤器-异常处理策略
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 15:49
 */
public interface AuthErrorStrategyFilter {

    /**
     * 执行方法
     * @param e 异常对象
     * @return 输出对象(请提前序列化)
     */
    Object run(Throwable e);
}
