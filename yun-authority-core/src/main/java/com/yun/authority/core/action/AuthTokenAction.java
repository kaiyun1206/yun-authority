package com.yun.authority.core.action;

import com.yun.authority.core.session.AuthSession;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 *     Token 逻辑代理接口
 *     此接口将会代理框架内部的一些关键性逻辑，方便开发者进行按需重写
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 14:10
 */
public interface AuthTokenAction {

    /**
     * 创建一个Token
     * @param loginId 账号id
     * @param loginType 账号类型
     * @return token
     */
    String createToken(Object loginId, String loginType);

    /**
     * 创建一个Session
     * @param sessionId Session的Id
     * @return 创建后的Session
     */
    AuthSession createSession(String sessionId);

    /**
     * 判断：集合中是否包含指定元素（模糊匹配）
     * @param list 集合
     * @param element 元素
     * @return 是否包含
     */
    boolean hasElement(List<String> list, String element);

    /**
     * 对一个Method对象进行注解检查（注解鉴权内部实现）
     * @param method Method对象
     */
    void checkMethodAnnotation(Method method);
}
