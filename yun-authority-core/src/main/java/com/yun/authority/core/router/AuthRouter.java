package com.yun.authority.core.router;

import com.yun.authority.core.AuthManager;
import com.yun.authority.core.context.AuthHolder;
import com.yun.authority.core.exception.BackResultException;
import com.yun.authority.core.exception.StopMatchException;
import com.yun.authority.core.function.AuthFunction;
import com.yun.authority.core.function.IsRunFunction;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     路由匹配操作工具类
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-11 15:55
 */
public class AuthRouter {

    // -------------------- 路由匹配相关 --------------------

    /**
     * 路由匹配
     * @param pattern 路由匹配符
     * @param path 被匹配的路由
     * @return 是否匹配成功
     */
    public static boolean isMatch(String pattern, String path) {
        return AuthManager.getAuthTokenContext().matchPath(pattern, path);
    }

    /**
     * 路由匹配
     * @param patterns 路由匹配符集合
     * @param path 被匹配的路由
     * @return 是否匹配成功
     */
    public static boolean isMatch(List<String> patterns, String path) {
        for (String pattern : patterns) {
            if(isMatch(pattern, path)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 路由匹配 (使用当前URI)
     * @param pattern 路由匹配符
     * @return 是否匹配成功
     */
    public static boolean isMatchCurrURI(String pattern) {
        return isMatch(pattern, AuthHolder.getRequest().getRequestPath());
    }

    /**
     * 路由匹配 (使用当前URI)
     * @param patterns 路由匹配符集合
     * @return 是否匹配成功
     */
    public static boolean isMatchCurrURI(List<String> patterns) {
        return isMatch(patterns, AuthHolder.getRequest().getRequestPath());
    }


    // -------------------- 执行相关 --------------------

    /**
     * 路由匹配，如果匹配成功则执行认证函数
     * @param pattern 路由匹配符
     * @param function 要执行的方法
     */
    public static void match(String pattern, AuthFunction function) {
        if(isMatchCurrURI(pattern)) {
            function.run();
        }
    }

    /**
     * 路由匹配 (并指定排除匹配符)，如果匹配成功则执行认证函数
     * @param pattern 路由匹配符
     * @param excludePattern 要排除的路由匹配符
     * @param function 要执行的方法
     */
    public static void match(String pattern, String excludePattern, AuthFunction function) {
        if(isMatchCurrURI(pattern)) {
            if(isMatchCurrURI(excludePattern) == false) {
                function.run();
            }
        }
    }

    /**
     * 路由匹配，如果匹配成功则执行认证函数
     * @param patterns 路由匹配符集合
     * @param function 要执行的方法
     */
    public static void match(List<String> patterns, AuthFunction function) {
        if(isMatchCurrURI(patterns)) {
            function.run();
        }
    }

    /**
     * 路由匹配 (并指定排除匹配符)，如果匹配成功则执行认证函数
     * @param patterns 路由匹配符集合
     * @param excludePatterns 要排除的路由匹配符集合
     * @param function 要执行的方法
     */
    public static void match(List<String> patterns, List<String> excludePatterns, AuthFunction function) {
        if(isMatchCurrURI(patterns)) {
            if(isMatchCurrURI(excludePatterns) == false) {
                function.run();
            }
        }
    }


    /**
     * 路由匹配，如果匹配成功则执行认证函数
     * @param patterns 路由匹配符集合
     * @return 匹配结果包装对象
     */
    public static IsRunFunction match(String... patterns) {
        boolean matchResult = isMatch(Arrays.asList(patterns), AuthHolder.getRequest().getRequestPath());
        return new IsRunFunction(matchResult);
    }


    // -------------------- 其它操作 --------------------

    /**
     * 停止匹配，跳出函数 (在多个匹配链中一次性跳出Auth函数)
     */
    public static void stop() {
        throw new StopMatchException();
    }

    /**
     * 停止匹配，结束执行，向前端返回结果
     * @param result 要输出的结果
     */
    public static void back(Object result) {
        throw new BackResultException(result);
    }

    /**
     * 停止匹配，结束执行，向前端返回结果
     */
    public static void back() {
        throw new BackResultException("");
    }

}
