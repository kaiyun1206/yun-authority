package com.yun.authority.core.router;

import com.yun.authority.core.context.model.AuthRequest;
import com.yun.authority.core.context.model.AuthResponse;

/**
 * <p>
 *     路由拦截器验证方法Lambda
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 15:53
 */
public interface AuthRouteFunction {

    /**
     * 执行验证的方法
     *
     * @param request  Request包装对象
     * @param response RespAuth包装对象
     * @param handler  处理对象
     */
    void run(AuthRequest request, AuthResponse response, Object handler);
}
