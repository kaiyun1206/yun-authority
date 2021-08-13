package com.yun.authority.core.context.model;

/**
 * <p>
 *     Response 包装类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:15
 */
public interface AuthResponse {

    /**
     * 获取底层源对象
     * @return see note
     */
    Object getSource();

    /**
     * 删除指定Cookie
     * @param name Cookie名称
     */
    void deleteCookie(String name);

    /**
     * 写入指定Cookie
     * @param name     Cookie名称
     * @param value    Cookie值
     * @param path     Cookie路径
     * @param domain   Cookie的作用域
     * @param timeout  过期时间 （秒）
     */
    void addCookie(String name, String value, String path, String domain, int timeout);

    /**
     * 在响应头里写入一个值
     * @param name 名字
     * @param value 值
     * @return 对象自身
     */
    AuthResponse setHeader(String name, String value);

    /**
     * 在响应头写入 [Server] 服务器名称
     * @param value 服务器名称
     * @return 对象自身
     */
    default AuthResponse setServer(String value) {
        return this.setHeader("Server", value);
    }

    /**
     * 重定向
     * @param url 重定向地址
     * @return 任意值
     */
    Object redirect(String url);
}
