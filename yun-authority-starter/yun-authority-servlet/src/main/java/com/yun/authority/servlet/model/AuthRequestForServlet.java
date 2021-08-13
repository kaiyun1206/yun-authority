package com.yun.authority.servlet.model;

import com.yun.authority.core.context.model.AuthRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     Request for Servlet
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 18:08
 */
public class AuthRequestForServlet implements AuthRequest {

    /**
     * 底层Request对象
     */
    HttpServletRequest request;

    /**
     * 实例化
     * @param request request对象
     */
    public AuthRequestForServlet(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取底层源对象
     */
    @Override
    public Object getSource() {
        return request;
    }

    /**
     * 在 [请求体] 里获取一个值
     */
    @Override
    public String getParam(String name) {
        return request.getParameter(name);
    }

    /**
     * 在 [请求头] 里获取一个值
     */
    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }

    /**
     * 在 [Cookie作用域] 里获取一个值
     */
    @Override
    public String getCookieValue(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 返回当前请求path (不包括上下文名称)
     */
    @Override
    public String getRequestPath() {
        return request.getServletPath();
    }

    /**
     * 返回当前请求的url，例：http://xxx.com/?id=127
     * @return see note
     */
    public String getUrl() {
        return request.getRequestURL().toString();
    }

    /**
     * 返回当前请求的类型
     */
    @Override
    public String getMethod() {
        return request.getMethod();
    }

}
