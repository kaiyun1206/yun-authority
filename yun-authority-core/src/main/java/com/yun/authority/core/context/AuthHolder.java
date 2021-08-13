package com.yun.authority.core.context;

import com.yun.authority.core.AuthManager;
import com.yun.authority.core.context.model.AuthRequest;
import com.yun.authority.core.context.model.AuthResponse;
import com.yun.authority.core.context.model.AuthStorage;

/**
 * <p>
 *     Token 上下文持有类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:18
 */
public class AuthHolder {

    /**
     * 获取当前请求的 [Request] 对象
     *
     * @return see note
     */
    public static AuthRequest getRequest() {
        return AuthManager.getAuthTokenContext().getRequest();
    }

    /**
     * 获取当前请求的 [Response] 对象
     *
     * @return see note
     */
    public static AuthResponse getResponse() {
        return AuthManager.getAuthTokenContext().getResponse();
    }

    /**
     * 获取当前请求的 [存储器] 对象
     *
     * @return see note
     */
    public static AuthStorage getStorage() {
        return AuthManager.getAuthTokenContext().getStorage();
    }

}
