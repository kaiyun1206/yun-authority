package com.yun.authority.core.listener;

import com.yun.authority.core.AuthManager;
import com.yun.authority.core.auth.AuthLoginModel;
import com.yun.footstone.common.date.DateFormatter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * <p>
 *     Token 侦听器的默认实现：log打印
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:37
 */
@Slf4j
public class AuthTokenListenerDefaultImpl implements AuthTokenListener {

    /**
     * 每次登录时触发
     */
    @Override
    public void doLogin(String loginType, Object loginId, AuthLoginModel loginModel) {
        println("账号[" + loginId + "]登录成功");
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        println("账号[" + loginId + "]注销成功");
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doLogoutByLoginId(String loginType, Object loginId, String tokenValue, String device) {
        println("账号[" + loginId + "]被踢下线 (终端: " + device + ")");
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue, String device) {
        println("账号[" + loginId + "]被顶下线 (终端: " + device + ")");
    }

    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        Date date = new Date(System.currentTimeMillis() + disableTime * 1000);
        println("账号[" + loginId + "]被封禁 (解封时间: " + DateFormatter.formatDateTime(date) + ")");
    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        println("账号[" + loginId + "]被解除封禁");
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
        println("Session[" + id + "]创建成功");
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
        println("Session[" + id + "]注销成功");
    }

    /**
     * 日志输出的前缀
     */
    public static final String LOG_PREFIX = "AuthLog -->: ";

    /**
     * 打印指定字符串
     * @param str 字符串
     */
    public void println(String str) {
        if(AuthManager.getConfig().getIsLog()) {
            log.info(LOG_PREFIX + str);
        }
    }

}
