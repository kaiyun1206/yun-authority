package com.yun.authority.core.context;

import com.yun.authority.core.context.model.AuthRequest;
import com.yun.authority.core.context.model.AuthResponse;
import com.yun.authority.core.context.model.AuthStorage;

/**
 * <p>
 *     Token 上下文处理器 [ThreadLocal版本]
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 14:46
 */
public class AuthTokenContextForThreadLocal implements AuthTokenContext {

    @Override
    public AuthRequest getRequest() {
        return AuthTokenContextForThreadLocalStorage.getRequest();
    }

    @Override
    public AuthResponse getResponse() {
        return AuthTokenContextForThreadLocalStorage.getResponse();
    }

    @Override
    public AuthStorage getStorage() {
        return AuthTokenContextForThreadLocalStorage.getStorage();
    }

    @Override
    public boolean matchPath(String pattern, String path) {
        return false;
    }

}
