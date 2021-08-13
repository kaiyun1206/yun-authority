package com.yun.authority.core.interim;

import com.yun.authority.core.AuthManager;

/**
 * <p>
 *     Token 临时验证令牌模块
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:52
 */
public class AuthInterimUtil {

    /**
     * 根据value创建一个token
     * @param value 指定值
     * @param timeout 有效期，单位：秒
     * @return 生成的token
     */
    public static String createToken(Object value, long timeout) {
        return AuthManager.getAuthInterim().createToken(value, timeout);
    }

    /**
     *  解析token获取value
     * @param token 指定token
     * @return  See Note
     */
    public static Object parseToken(String token) {
        return AuthManager.getAuthInterim().parseToken(token);
    }

    /**
     * 解析token获取value，并转换为指定类型
     * @param token 指定token
     * @param cs 指定类型
     * @param <T> 默认值的类型
     * @return  See Note
     */
    public static<T> T parseToken(String token, Class<T> cs) {
        return AuthManager.getAuthInterim().parseToken(token, cs);
    }

    /**
     * 获取指定 token 的剩余有效期，单位：秒
     * <p> 返回值 -1 代表永久，-2 代表token无效
     * @param token see note
     * @return see note
     */
    public static long getTimeout(String token) {
        return AuthManager.getAuthInterim().getTimeout(token);
    }
}
