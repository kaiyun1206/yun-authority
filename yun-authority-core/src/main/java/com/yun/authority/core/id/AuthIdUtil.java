package com.yun.authority.core.id;

/**
 * <p>
 *     Token-Id 身份凭证模块-工具类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 16:39
 */
public class AuthIdUtil {

    /**
     * 在 Request 上储存 Id-Token 时建议使用的key
     */
    public static final String ID_TOKEN = AuthIdTemplate.ID_TOKEN;

    /**
     * 底层 AuthIdTemplate 对象
     */
    public static AuthIdTemplate authIdTemplate = new AuthIdTemplate();

    // -------------------- 获取 & 校验

    /**
     * 获取当前Id-Token, 如果不存在，则立即创建并返回
     * @return 当前token
     */
    public static String getToken() {
        return authIdTemplate.getToken();
    }

    /**
     * 判断一个Id-Token是否有效
     * @param token 要验证的token
     * @return 这个token是否有效
     */
    public static boolean isValid(String token) {
        return authIdTemplate.isValid(token);
    }

    /**
     * 校验一个Id-Token是否有效 (如果无效则抛出异常)
     * @param token 要验证的token
     */
    public static void checkToken(String token) {
        authIdTemplate.checkToken(token);
    }

    /**
     * 校验当前Request提供的Id-Token是否有效 (如果无效则抛出异常)
     */
    public static void checkCurrentRequestToken() {
        authIdTemplate.checkCurrentRequestToken();
    }

    /**
     * 刷新一次Id-Token (注意集群环境中不要多个服务重复调用)
     * @return 新Token
     */
    public static String refreshToken() {
        return authIdTemplate.refreshToken();
    }


    // -------------------- 获取Token

    /**
     * 获取Id-Token，不做任何处理
     * @return token
     */
    public static String getTokenNh() {
        return authIdTemplate.getTokenNh();
    }

    /**
     * 获取Past-Id-Token，不做任何处理
     * @return token
     */
    public static String getPastTokenNh() {
        return authIdTemplate.getPastTokenNh();
    }

}
