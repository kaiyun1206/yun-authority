package com.yun.authority.core.interim;

import com.yun.authority.core.AuthManager;
import com.yun.authority.core.util.AuthFoxUtil;

/**
 * <p>
 *     Token 临时令牌验证模块接口
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 15:43
 */
public interface AuthInterimInterface {

    /**
     * 根据value创建一个token
     * @param value 指定值
     * @param timeout 有效期，单位：秒
     * @return 生成的token
     */
    default String createToken(Object value, long timeout) {

        // 生成 token
        String token = AuthManager.getAuthTokenAction().createToken(null, null);

        // 持久化映射关系
        String key = splicingKeyTempToken(token);
        AuthManager.getAuthTokenDao().setObject(key, value, timeout);

        // 返回
        return token;
    }

    /**
     *  解析token获取value
     * @param token 指定token
     * @return  See Note
     */
    default Object parseToken(String token) {
        String key = splicingKeyTempToken(token);
        return AuthManager.getAuthTokenDao().getObject(key);
    }

    /**
     * 解析token获取value，并转换为指定类型
     * @param token 指定token
     * @param cs 指定类型
     * @param <T> 默认值的类型
     * @return  See Note
     */
    default<T> T parseToken(String token, Class<T> cs) {
        return AuthFoxUtil.getValueByType(parseToken(token), cs);
    }

    /**
     * 获取指定 token 的剩余有效期，单位：秒
     * <p> 返回值 -1 代表永久，-2 代表token无效
     * @param token see note
     * @return see note
     */
    default long getTimeout(String token) {
        String key = splicingKeyTempToken(token);
        return AuthManager.getAuthTokenDao().getObjectTimeout(key);
    }

    /**
     * 获取映射关系的持久化key
     * @param token token值
     * @return key
     */
    default String splicingKeyTempToken(String token) {
        return AuthManager.getConfig().getTokenName() + ":temp-token:" + token;
    }

    /**
     * @return jwt秘钥 (只有集成 yun-auth-temp-jwt 模块时此参数才会生效)
     */
    default String getJwtSecretKey() {
        return null;
    }
}
