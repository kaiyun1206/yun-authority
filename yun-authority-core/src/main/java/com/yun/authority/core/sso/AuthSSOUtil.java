package com.yun.authority.core.sso;

import com.yun.authority.core.auth.AuthUtil;
import com.yun.authority.core.sso.AuthSSOTemplate.*;

/**
 * <p>
 *     Token-SSO 单点登录模块 工具类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 23:56
 */
public class AuthSSOUtil {

    /**
     * 底层 AuthSSOTemplate 对象
     */
    public static AuthSSOTemplate authSSOTemplate = new AuthSSOTemplate(AuthUtil.authLogic);


    // ---------------------- Ticket 操作 ----------------------

    /**
     * 根据 账号id 创建一个 Ticket码
     * @param loginId 账号id
     * @return Ticket码
     */
    public static String createTicket(Object loginId) {
        return authSSOTemplate.createTicket(loginId);
    }

    /**
     * 删除 Ticket
     * @param ticket Ticket码
     */
    public static void deleteTicket(String ticket) {
        authSSOTemplate.deleteTicket(ticket);
    }

    /**
     * 删除 Ticket索引
     * @param loginId 账号id
     */
    public static void deleteTicketIndex(Object loginId) {
        authSSOTemplate.deleteTicketIndex(loginId);
    }

    /**
     * 根据 Ticket码 获取账号id，如果Ticket码无效则返回null
     * @param ticket Ticket码
     * @return 账号id
     */
    public static Object getLoginId(String ticket) {
        return authSSOTemplate.getLoginId(ticket);
    }

    /**
     * 根据 Ticket码 获取账号id，并转换为指定类型
     * @param <T> 要转换的类型
     * @param ticket Ticket码
     * @param cs 要转换的类型
     * @return 账号id
     */
    public static <T> T getLoginId(String ticket, Class<T> cs) {
        return authSSOTemplate.getLoginId(ticket, cs);
    }

    /**
     * 校验ticket码，获取账号id，如果此ticket是有效的，则立即删除
     * @param ticket Ticket码
     * @return 账号id
     */
    public static Object checkTicket(String ticket) {
        return authSSOTemplate.checkTicket(ticket);
    }


    // ---------------------- 构建URL ----------------------

    /**
     * 构建URL：Server端 单点登录地址
     * @param clientLoginUrl Client端登录地址
     * @param back 回调路径
     * @return [SSO-Server端-认证地址 ]
     */
    public static String buildServerAuthUrl(String clientLoginUrl, String back) {
        return authSSOTemplate.buildServerAuthUrl(clientLoginUrl, back);
    }

    /**
     * 构建URL：Server端向Client下放ticke的地址
     * @param loginId 账号id
     * @param redirect Client端提供的重定向地址
     * @return see note
     */
    public static String buildRedirectUrl(Object loginId, String redirect) {
        return authSSOTemplate.buildRedirectUrl(loginId, redirect);
    }

    /**
     * 校验重定向url合法性
     * @param url 下放ticket的url地址
     */
    public static void checkAuthUrl(String url) {
        authSSOTemplate.checkRedirectUrl(url);
    }

    /**
     * 构建URL：Server端 账号资料查询地址
     * @param loginId 账号id
     * @return Server端 账号资料查询地址
     */
    public static String buildUserinfoUrl(Object loginId) {
        return authSSOTemplate.buildUserinfoUrl(loginId);
    }

    // ------------------- SSO 模式三 -------------------

    /**
     * 校验secretkey秘钥是否有效
     * @param secretkey 秘钥
     */
    public static void checkSecretkey(String secretkey) {
        authSSOTemplate.checkSecretkey(secretkey);
    }

    /**
     * 构建URL：校验ticket的URL
     * @param ticket ticket码
     * @param ssoLogoutCallUrl 单点注销时的回调URL
     * @return 构建完毕的URL
     */
    public static String buildCheckTicketUrl(String ticket, String ssoLogoutCallUrl) {
        return authSSOTemplate.buildCheckTicketUrl(ticket, ssoLogoutCallUrl);
    }

    /**
     * 为指定账号id注册单点注销回调URL
     * @param loginId 账号id
     * @param sloCallbackUrl 单点注销时的回调URL
     */
    public static void registerSloCallbackUrl(Object loginId, String sloCallbackUrl) {
        authSSOTemplate.registerSloCallbackUrl(loginId, sloCallbackUrl);
    }

    /**
     * 循环调用Client端单点注销回调
     * @param loginId 账号id
     * @param fun 调用方法
     */
    public static void forEachSloUrl(Object loginId, CallSloUrlFunction fun) {
        authSSOTemplate.forEachSloUrl(loginId, fun);
    }

    /**
     * 构建URL：单点注销URL
     * @param loginId 要注销的账号id
     * @return 单点注销URL
     */
    public static String buildSloUrl(Object loginId) {
        return authSSOTemplate.buildSloUrl(loginId);
    }

    /**
     * 指定账号单点注销
     * @param secretkey 校验秘钥
     * @param loginId 指定账号
     * @param fun 调用方法
     */
    public static void singleLogout(String secretkey, Object loginId, CallSloUrlFunction fun) {
        authSSOTemplate.singleLogout(secretkey, loginId, fun);
    }

    /**
     * 获取：账号资料
     * @param loginId 账号id
     * @return 账号资料
     */
    public static Object getUserinfo(Object loginId) {
        return authSSOTemplate.getUserinfo(loginId);
    }

}
