package com.yun.authority.spring.interceptor;

import com.yun.authority.core.auth.AuthUtil;
import com.yun.authority.core.exception.BackResultException;
import com.yun.authority.core.exception.StopMatchException;
import com.yun.authority.core.router.AuthRouteFunction;
import com.yun.authority.servlet.model.AuthRequestForServlet;
import com.yun.authority.servlet.model.AuthResponseForServlet;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *     token基于路由的拦截式鉴权
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 11:30
 */
public class AuthRouteInterceptor implements HandlerInterceptor {

    /**
     * 每次进入拦截器的[执行函数]
     */
    public AuthRouteFunction function;

    /**
     * 创建一个路由拦截器
     */
    public AuthRouteInterceptor() {
    }

    /**
     * 创建, 并指定[执行函数]
     * @param function [执行函数]
     */
    public AuthRouteInterceptor(AuthRouteFunction function) {
        this.function = function;
    }

    /**
     * 静态方法快速构建一个
     * @param function 自定义模式下的执行函数
     * @return sa路由拦截器
     */
    public static AuthRouteInterceptor newInstance(AuthRouteFunction function) {
        return new AuthRouteInterceptor(function);
    }


    // ----------------- 验证方法 -----------------

    /**
     * 每次请求之前触发的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 如果未提供function，默认进行登录验证
        if(function == null) {
            AuthUtil.checkLogin();
        } else {
            // 否则执行认证函数
            try {
                function.run(new AuthRequestForServlet(request), new AuthResponseForServlet(response), handler);
            } catch (StopMatchException e) {
                // 停止匹配，进入Controller
            } catch (BackResultException e) {
                // 停止匹配，向前端输出结果
                if(response.getContentType() == null) {
                    response.setContentType("text/plain; charset=utf-8");
                }
                response.getWriter().print(e.getMessage());
                return false;
            }
        }

        // 通过验证
        return true;
    }

}
