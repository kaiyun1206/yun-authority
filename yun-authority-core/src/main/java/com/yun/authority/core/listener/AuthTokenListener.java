package com.yun.authority.core.listener;

import com.yun.authority.core.auth.AuthLoginModel;

/**
 * <p>
 *     Token 侦听器
 *     可以通过实现此接口在用户登陆、退出等关键性操作时进行一些AOP操作
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:35
 */
public interface AuthTokenListener {

    /**
     * 每次登录时触发
     * @param loginType 账号类别
     * @param loginId 账号id
     * @param loginModel 登录参数
     */
    void doLogin(String loginType, Object loginId, AuthLoginModel loginModel);

    /**
     * 每次注销时触发
     * @param loginType 账号类别
     * @param loginId 账号id
     * @param tokenValue token值
     */
    void doLogout(String loginType, Object loginId, String tokenValue);

    /**
     * 每次被踢下线时触发
     * @param loginType 账号类别
     * @param loginId 账号id
     * @param tokenValue token值
     * @param device 设备标识
     */
    void doLogoutByLoginId(String loginType, Object loginId, String tokenValue, String device);

    /**
     * 每次被顶下线时触发
     * @param loginType 账号类别
     * @param loginId 账号id
     * @param tokenValue token值
     * @param device 设备标识
     */
    void doReplaced(String loginType, Object loginId, String tokenValue, String device);

    /**
     * 每次被封禁时触发
     * @param loginType 账号类别
     * @param loginId 账号id
     * @param disableTime 封禁时长，单位: 秒
     */
    void doDisable(String loginType, Object loginId, long disableTime);

    /**
     * 每次被解封时触发
     * @param loginType 账号类别
     * @param loginId 账号id
     */
    void doUntieDisable(String loginType, Object loginId);

    /**
     * 每次创建Session时触发
     * @param id SessionId
     */
    void doCreateSession(String id);

    /**
     * 每次注销Session时触发
     * @param id SessionId
     */
    void doLogoutSession(String id);

}
