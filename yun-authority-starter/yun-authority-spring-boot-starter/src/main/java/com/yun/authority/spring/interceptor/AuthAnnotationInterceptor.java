package com.yun.authority.spring.interceptor;

import com.yun.authority.core.AuthManager;
import org.apache.catalina.Manager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>
 *     注解式鉴权 - 拦截器
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 11:22
 */
public class AuthAnnotationInterceptor implements HandlerInterceptor {

    /**
     * 构建： 注解式鉴权 - 拦截器
     */
    public AuthAnnotationInterceptor() {
    }

    /**
     * 每次请求之前触发的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 获取处理method
        if (handler instanceof HandlerMethod == false) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();

        // 进行验证
        AuthManager.getAuthTokenAction().checkMethodAnnotation(method);

        // 通过验证
        return true;
    }
}
