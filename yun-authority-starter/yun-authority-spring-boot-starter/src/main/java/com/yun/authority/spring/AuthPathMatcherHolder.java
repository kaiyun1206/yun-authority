package com.yun.authority.spring;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * <p>
 *     路由匹配器
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 14:08
 */
public class AuthPathMatcherHolder {

    /**
     * 路由匹配器
     */
    public static PathMatcher pathMatcher;

    /**
     * 获取路由匹配器
     * @return 路由匹配器
     */
    public static PathMatcher getPathMatcher() {
        if(pathMatcher == null) {
            pathMatcher = new AntPathMatcher();
        }
        return pathMatcher;
    }

    /**
     * 写入路由匹配器
     * @param pathMatcher 路由匹配器
     */
    public static void setPathMatcher(PathMatcher pathMatcher) {
        AuthPathMatcherHolder.pathMatcher = pathMatcher;
    }

}
