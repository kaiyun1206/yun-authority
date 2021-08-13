package com.yun.authority.spring;

import com.yun.authority.core.context.AuthTokenContext;
import com.yun.authority.core.context.model.AuthRequest;
import com.yun.authority.core.context.model.AuthResponse;
import com.yun.authority.core.context.model.AuthStorage;
import com.yun.authority.servlet.model.AuthRequestForServlet;
import com.yun.authority.servlet.model.AuthResponseForServlet;
import com.yun.authority.servlet.model.AuthStorageForServlet;

/**
 * <p>
 *     yun-authority 对Cookie的相关操作 接口实现类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 14:33
 */
public class AuthTokenContextForSpring implements AuthTokenContext {

    /**
     * 获取当前请求的Request对象
     */
    @Override
    public AuthRequest getRequest() {
        return new AuthRequestForServlet(SpringMVCUtil.getRequest());
    }

    /**
     * 获取当前请求的Response对象
     */
    @Override
    public AuthResponse getResponse() {
        return new AuthResponseForServlet(SpringMVCUtil.getResponse());
    }

    /**
     * 获取当前请求的 [存储器] 对象
     */
    @Override
    public AuthStorage getStorage() {
        return new AuthStorageForServlet(SpringMVCUtil.getRequest());
    }

    /**
     * 校验指定路由匹配符是否可以匹配成功指定路径
     */
    @Override
    public boolean matchPath(String pattern, String path) {
        return AuthPathMatcherHolder.getPathMatcher().match(pattern, path);
    }


}
