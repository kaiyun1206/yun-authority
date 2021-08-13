package com.yun.authority.servlet.model;

import com.yun.authority.core.context.model.AuthStorage;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *     Storage for Servlet
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 18:15
 */
public class AuthStorageForServlet implements AuthStorage {

    /**
     * 底层Request对象
     */
    HttpServletRequest request;

    /**
     * 实例化
     * @param request request对象
     */
    public AuthStorageForServlet(HttpServletRequest request) {
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
     * 在 [Request作用域] 里写入一个值
     */
    @Override
    public void set(String key, Object value) {
        request.setAttribute(key, value);
    }

    /**
     * 在 [Request作用域] 里获取一个值
     */
    @Override
    public Object get(String key) {
        return request.getAttribute(key);
    }

    /**
     * 在 [Request作用域] 里删除一个值
     */
    @Override
    public void delete(String key) {
        request.removeAttribute(key);
    }

}
