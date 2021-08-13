package com.yun.authority.core.context;

import com.yun.authority.core.context.model.AuthRequest;
import com.yun.authority.core.context.model.AuthResponse;
import com.yun.authority.core.context.model.AuthStorage;

/**
 * <p>
 *     Token 上下文处理器
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:08
 */
public interface AuthTokenContext {

    /**
     * 获取当前请求的 [Request] 对象
     *
     * @return see note
     */
    AuthRequest getRequest();

    /**
     * 获取当前请求的 [Response] 对象
     *
     * @return see note
     */
    AuthResponse getResponse();

    /**
     * 获取当前请求的 [存储器] 对象
     *
     * @return see note
     */
    AuthStorage getStorage();

    /**
     * 校验指定路由匹配符是否可以匹配成功指定路径
     *
     * @param pattern 路由匹配符
     * @param path 需要匹配的路径
     * @return see note
     */
    boolean matchPath(String pattern, String path);
}
