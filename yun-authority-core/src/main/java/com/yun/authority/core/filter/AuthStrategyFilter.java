package com.yun.authority.core.filter;

/**
 * <p>
 *     Token全局过滤器-认证策略
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 15:47
 */
public interface AuthStrategyFilter {

    /**
     * 执行方法
     * @param r 无含义参数，留作扩展
     */
    void run(Object r);

}
