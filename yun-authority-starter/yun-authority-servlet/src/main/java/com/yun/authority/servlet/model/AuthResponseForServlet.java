package com.yun.authority.servlet.model;

import cn.hutool.core.util.StrUtil;
import com.yun.authority.core.context.model.AuthResponse;
import com.yun.authority.core.exception.AuthTokenException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     Response for Servlet
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 18:13
 */
public class AuthResponseForServlet implements AuthResponse {

    /**
     * 底层Request对象
     */
    HttpServletResponse response;

    /**
     * 实例化
     * @param response response对象
     */
    public AuthResponseForServlet(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 获取底层源对象
     */
    @Override
    public Object getSource() {
        return response;
    }

    /**
     * 删除指定Cookie
     */
    @Override
    public void deleteCookie(String name) {
        addCookie(name, null, null, null, 0);
    }

    /**
     * 写入指定Cookie
     */
    @Override
    public void addCookie(String name, String value, String path, String domain, int timeout) {
        Cookie cookie = new Cookie(name, value);
        if(StrUtil.isBlank(path)) {
            path = "/";
        }
        if(StrUtil.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(timeout);
        response.addCookie(cookie);
    }

    /**
     * 在响应头里写入一个值
     */
    @Override
    public AuthResponse setHeader(String name, String value) {
        response.setHeader(name, value);
        return this;
    }

    /**
     * 重定向
     */
    @Override
    public Object redirect(String url) {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new AuthTokenException(e);
        }
        return null;
    }

}
