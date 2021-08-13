package com.yun.authority.core.context;

import com.yun.authority.core.context.model.AuthRequest;
import com.yun.authority.core.context.model.AuthResponse;
import com.yun.authority.core.context.model.AuthStorage;
import com.yun.authority.core.exception.AuthTokenException;

/**
 * <p>
 *     Token 上下文处理器 [默认实现类]
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:32
 */
public class AuthTokenContextDefaultImpl implements AuthTokenContext {

    /**
     * 默认的错误提示语
     */
    public static final String ERROR_MESSAGE = "未初始化任何有效上下文处理器";

    /**
     * 获取当前请求的 [Request] 对象
     */
    @Override
    public AuthRequest getRequest() {
        throw new AuthTokenException(ERROR_MESSAGE);
    }

    /**
     * 获取当前请求的 [Response] 对象
     */
    @Override
    public AuthResponse getResponse() {
        throw new AuthTokenException(ERROR_MESSAGE);
    }

    /**
     * 获取当前请求的 [存储器] 对象
     */
    @Override
    public AuthStorage getStorage() {
        throw new AuthTokenException(ERROR_MESSAGE);
    }

    /**
     * 校验指定路由匹配符是否可以匹配成功指定路径
     */
    @Override
    public boolean matchPath(String pattern, String path) {
        throw new AuthTokenException(ERROR_MESSAGE);
    }
}
