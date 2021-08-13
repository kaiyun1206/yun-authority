package com.yun.authority.core.session;

import cn.hutool.db.Session;
import com.yun.authority.core.AuthManager;

/**
 * <p>
 *     自定义Session工具类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 15:42
 */
public class AuthSessionUtil {

    /**
     * 添加上指定前缀，防止恶意伪造Session
     */
    public static String sessionKey = "custom";

    /**
     * 拼接Key: 自定义Session的Id
     *
     * @param sessionId 会话id
     * @return sessionId
     */
    public static String splicingSessionKey(String sessionId) {
        return AuthManager.getConfig().getTokenName() + ":" + sessionKey + ":session:" + sessionId;
    }

    /**
     * 指定key的Session是否存在
     *
     * @param sessionId Session的id
     * @return 是否存在
     */
    public static boolean isExists(String sessionId) {
        return AuthManager.getAuthTokenDao().getSession(splicingSessionKey(sessionId)) != null;
    }

    /**
     * 获取指定key的Session
     *
     * @param sessionId key
     * @param isCreate  如果此Session尚未在DB创建，是否新建并返回
     * @return SaSession
     */
    public static AuthSession getSessionById(String sessionId, boolean isCreate) {
        AuthSession session = AuthManager.getAuthTokenDao().getSession(splicingSessionKey(sessionId));
        if (session == null && isCreate) {
            session = AuthManager.getAuthTokenAction().createSession(splicingSessionKey(sessionId));
            AuthManager.getAuthTokenDao().setSession(session, AuthManager.getConfig().getTimeout());
        }
        return session;
    }

    /**
     * 获取指定key的Session, 如果此Session尚未在DB创建，则新建并返回
     *
     * @param sessionId key
     * @return session对象
     */
    public static AuthSession getSessionById(String sessionId) {
        return getSessionById(sessionId, true);
    }

    /**
     * 删除指定key的Session 
     *
     * @param sessionId 指定key
     */
    public static void deleteSessionById(String sessionId) {
        AuthManager.getAuthTokenDao().deleteSession(splicingSessionKey(sessionId));
    }
    
}
