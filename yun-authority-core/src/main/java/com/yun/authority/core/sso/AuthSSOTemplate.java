package com.yun.authority.core.sso;

import cn.hutool.core.util.StrUtil;
import com.yun.authority.core.AuthManager;
import com.yun.authority.core.auth.AuthLogic;
import com.yun.authority.core.config.AuthSSOConfig;
import com.yun.authority.core.exception.AuthTokenException;
import com.yun.authority.core.session.AuthSession;
import com.yun.authority.core.sso.AuthSSOConstants.ParamName;
import com.yun.authority.core.util.AuthFoxUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *     Token-SSO 单点登录模块
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 23:58
 */
public class AuthSSOTemplate {

    /**
     * 单点登录模块使用的 AuthLogic 对象
     */
    public AuthLogic authLogic;
    public AuthSSOTemplate(AuthLogic authLogic) {
        this.authLogic = authLogic;
    }

    // ---------------------- Ticket 操作 ----------------------

    /**
     * 根据 账号id 创建一个 Ticket码
     * @param loginId 账号id
     * @return Ticket码
     */
    public String createTicket(Object loginId) {
        // 创建 Ticket
        String ticket = randomTicket(loginId);

        // 保存 Ticket
        saveTicket(ticket, loginId);
        saveTicketIndex(ticket, loginId);

        // 返回 Ticket
        return ticket;
    }

    /**
     * 保存 Ticket
     * @param ticket ticket码
     * @param loginId 账号id
     */
    public void saveTicket(String ticket, Object loginId) {
        long ticketTimeout = AuthManager.getConfig().getSSO().getTicketTimeout();
        AuthManager.getAuthTokenDao().set(splicingTicketSaveKey(ticket), String.valueOf(loginId), ticketTimeout);
    }

    /**
     * 保存 Ticket 索引
     * @param ticket ticket码
     * @param loginId 账号id
     */
    public void saveTicketIndex(String ticket, Object loginId) {
        long ticketTimeout = AuthManager.getConfig().getSSO().getTicketTimeout();
        AuthManager.getAuthTokenDao().set(splicingTicketIndexKey(loginId), String.valueOf(ticket), ticketTimeout);
    }

    /**
     * 删除 Ticket
     * @param ticket Ticket码
     */
    public void deleteTicket(String ticket) {
        if(ticket == null) {
            return;
        }
        AuthManager.getAuthTokenDao().delete(splicingTicketSaveKey(ticket));
    }

    /**
     * 删除 Ticket索引
     * @param loginId 账号id
     */
    public void deleteTicketIndex(Object loginId) {
        if(loginId == null) {
            return;
        }
        AuthManager.getAuthTokenDao().delete(splicingTicketIndexKey(loginId));
    }

    /**
     * 根据 Ticket码 获取账号id，如果Ticket码无效则返回null
     * @param ticket Ticket码
     * @return 账号id
     */
    public Object getLoginId(String ticket) {
        if(StrUtil.isBlank(ticket)) {
            return null;
        }
        return AuthManager.getAuthTokenDao().get(splicingTicketSaveKey(ticket));
    }

    /**
     * 根据 Ticket码 获取账号id，并转换为指定类型
     * @param <T> 要转换的类型
     * @param ticket Ticket码
     * @param cs 要转换的类型
     * @return 账号id
     */
    public <T> T getLoginId(String ticket, Class<T> cs) {
        return AuthFoxUtil.getValueByType(getLoginId(ticket), cs);
    }

    /**
     * 查询 指定账号id的 Ticket值
     * @param loginId 账号id
     * @return Ticket值
     */
    public String getTicketValue(Object loginId) {
        if(loginId == null) {
            return null;
        }
        return AuthManager.getAuthTokenDao().get(splicingTicketIndexKey(loginId));
    }

    /**
     * 校验ticket码，获取账号id，如果此ticket是有效的，则立即删除
     * @param ticket Ticket码
     * @return 账号id
     */
    public Object checkTicket(String ticket) {
        Object loginId = getLoginId(ticket);
        if(loginId != null) {
            deleteTicket(ticket);
            deleteTicketIndex(loginId);
        }
        return loginId;
    }

    /**
     * 随机一个 Ticket码
     * @param loginId 账号id
     * @return Ticket码
     */
    public String randomTicket(Object loginId) {
        return AuthFoxUtil.getRandomString(64);
    }


    // ---------------------- 构建URL ----------------------

    /**
     * 构建URL：Server端 单点登录地址
     * @param clientLoginUrl Client端登录地址
     * @param back 回调路径
     * @return [SSO-Server端-认证地址 ]
     */
    public String buildServerAuthUrl(String clientLoginUrl, String back) {

        // 服务端认证地址
        String serverUrl = AuthManager.getConfig().getSSO().getAuthUrl();

        // 对back地址编码
        back = (back == null ? "" : back);
        back = AuthFoxUtil.encodeUrl(back);

        // 拼接最终地址，格式示例：serverAuthUrl = http://xxx.com?redirectUrl=xxx.com?back=xxx.com
        clientLoginUrl = AuthFoxUtil.joinParam(clientLoginUrl, ParamName.back, back);
        String serverAuthUrl = AuthFoxUtil.joinParam(serverUrl, ParamName.redirect, clientLoginUrl);

        // 返回
        return serverAuthUrl;
    }

    /**
     * 构建URL：Server端向Client下放ticke的地址
     * @param loginId 账号id
     * @param redirect Client端提供的重定向地址
     * @return see note
     */
    public String buildRedirectUrl(Object loginId, String redirect) {

        // 校验 重定向地址 是否合法
        checkRedirectUrl(redirect);

        // 删掉 旧Ticket
        deleteTicket(getTicketValue(loginId));

        // 创建 新Ticket
        String ticket = createTicket(loginId);

        // 构建 授权重定向地址 （Server端 根据此地址向 Client端 下放Ticket）
        return AuthFoxUtil.joinParam(encodeBackParam(redirect), ParamName.ticket, ticket);
    }

    /**
     * 校验重定向url合法性
     * @param url 下放ticket的url地址
     */
    public void checkRedirectUrl(String url) {

        // 1、是否是一个有效的url
        if(AuthFoxUtil.isUrl(url) == false) {
            throw new AuthTokenException("无效redirect：" + url);
        }

        // 2、截取掉?后面的部分
        int qIndex = url.indexOf("?");
        if(qIndex != -1) {
            url = url.substring(0, qIndex);
        }

        // 3、是否在[允许地址列表]之中
        String authUrl = AuthManager.getConfig().getSSO().getAllowUrl().replaceAll(" ", "");
        List<String> authUrlList = Arrays.asList(authUrl.split(","));
        if(AuthManager.getAuthTokenAction().hasElement(authUrlList, url) == false) {
            throw new AuthTokenException("非法redirect：" + url);
        }

        // 校验通过 √
        return;
    }

    /**
     * 对url中的back参数进行URL编码, 解决超链接重定向后参数丢失的bug
     * @param url url
     * @return 编码过后的url
     */
    public String encodeBackParam(String url) {

        // 获取back参数所在位置
        int index = url.indexOf("?" + ParamName.back + "=");
        if(index == -1) {
            index = url.indexOf("&" + ParamName.back + "=");
            if(index == -1) {
                return url;
            }
        }

        // 开始编码
        int length = ParamName.back.length() + 2;
        String back = url.substring(index + length);
        back = AuthFoxUtil.encodeUrl(back);

        // 放回url中
        url = url.substring(0, index + length) + back;
        return url;
    }

    /**
     * 构建URL：Server端 账号资料查询地址
     * @param loginId 账号id
     * @return Server端 账号资料查询地址
     */
    public String buildUserinfoUrl(Object loginId) {
        // 拼接
        String userinfoUrl = AuthManager.getConfig().getSSO().getUserinfoUrl();
        userinfoUrl = AuthFoxUtil.joinParam(userinfoUrl, ParamName.loginId, loginId);
        userinfoUrl = AuthFoxUtil.joinParam(userinfoUrl, ParamName.secretkey, AuthManager.getConfig().getSSO().getSecretKey());
        // 返回
        return userinfoUrl;
    }


    // ------------------- SSO 模式三相关 -------------------

    /**
     * 校验secretkey秘钥是否有效
     * @param secretkey 秘钥
     */
    public void checkSecretkey(String secretkey) {
        if(secretkey == null || secretkey.isEmpty() || secretkey.equals(AuthManager.getConfig().getSSO().getSecretKey()) == false) {
            throw new AuthTokenException("无效秘钥：" + secretkey);
        }
    }

    /**
     * 构建URL：校验ticket的URL
     * <p> 在模式三下，Client端拿到Ticket后根据此地址向Server端发送请求，获取账号id
     * @param ticket ticket码
     * @param ssoLogoutCallUrl 单点注销时的回调URL
     * @return 构建完毕的URL
     */
    public String buildCheckTicketUrl(String ticket, String ssoLogoutCallUrl) {
        // 裸地址
        String url = AuthManager.getConfig().getSSO().getCheckTicketUrl();

        // 拼接ticket参数
        url = AuthFoxUtil.joinParam(url, ParamName.ticket, ticket);

        // 拼接单点注销时的回调URL
        if(ssoLogoutCallUrl != null) {
            url = AuthFoxUtil.joinParam(url, ParamName.ssoLogoutCall, ssoLogoutCallUrl);
        }

        // 返回
        return url;
    }

    /**
     * 为指定账号id注册单点注销回调URL
     * @param loginId 账号id
     * @param sloCallbackUrl 单点注销时的回调URL
     */
    public void registerSloCallbackUrl(Object loginId, String sloCallbackUrl) {
        if(loginId == null || sloCallbackUrl == null || sloCallbackUrl.isEmpty()) {
            return;
        }
        AuthSession session = authLogic.getSessionByLoginId(loginId);
        Set<String> urlSet = session.get(AuthSSOConstants.SLO_CALLBACK_SET_KEY, ()-> new HashSet<String>());
        urlSet.add(sloCallbackUrl);
        session.set(AuthSSOConstants.SLO_CALLBACK_SET_KEY, urlSet);
    }

    /**
     * 循环调用Client端单点注销回调
     * @param loginId 账号id
     * @param fun 调用方法
     */
    public void forEachSloUrl(Object loginId, CallSloUrlFunction fun) {
        String secretkey = AuthManager.getConfig().getSSO().getSecretKey();
        Set<String> urlSet = authLogic.getSessionByLoginId(loginId).get(AuthSSOConstants.SLO_CALLBACK_SET_KEY,
                () -> new HashSet<String>());

        for (String url : urlSet) {
            // 拼接：login参数、秘钥参数
            url = AuthFoxUtil.joinParam(url, ParamName.loginId, loginId);
            url = AuthFoxUtil.joinParam(url, ParamName.secretkey, secretkey);
            // 调用
            fun.run(url);
        }
    }

    /**
     * 构建URL：单点注销URL
     * @param loginId 要注销的账号id
     * @return 单点注销URL
     */
    public String buildSloUrl(Object loginId) {
        AuthSSOConfig ssoConfig = AuthManager.getConfig().getSSO();
        String url = ssoConfig.getSloUrl();
        url = AuthFoxUtil.joinParam(url, ParamName.loginId, loginId);
        url = AuthFoxUtil.joinParam(url, ParamName.secretkey, ssoConfig.getSecretKey());
        return url;
    }

    /**
     * 指定账号单点注销
     * @param secretkey 校验秘钥
     * @param loginId 指定账号
     * @param fun 调用方法
     */
    public void singleLogout(String secretkey, Object loginId, CallSloUrlFunction fun) {
        // step.1 校验秘钥
        checkSecretkey(secretkey);

        // step.2 遍历通知Client端注销会话
        forEachSloUrl(loginId, fun);

        // step.3 Server端注销
        // StpUtil.logoutByLoginId(loginId);
        authLogic.logoutByTokenValue(authLogic.getTokenValueByLoginId(loginId));
    }

    /**
     * 获取：账号资料
     * @param loginId 账号id
     * @return 账号资料
     */
    public Object getUserinfo(Object loginId) {
        String url = buildUserinfoUrl(loginId);
        return AuthManager.getConfig().getSSO().sendHttp.apply(url);
    }



    // ------------------- 返回相应key -------------------

    /**
     * 拼接key：Ticket 查 账号Id
     * @param ticket ticket值
     * @return key
     */
    public String splicingTicketSaveKey(String ticket) {
        return AuthManager.getConfig().getTokenName() + ":ticket:" + ticket;
    }

    /**
     * 拼接key：账号Id 反查 Ticket
     * @param id 账号id
     * @return key
     */
    public String splicingTicketIndexKey(Object id) {
        return AuthManager.getConfig().getTokenName() + ":id-ticket:" + id;
    }

    /**
     * 单点注销回调函数
     * @author kong
     */
    @FunctionalInterface
    interface CallSloUrlFunction{
        /**
         * 调用function
         * @param url 注销回调URL
         */
        void run(String url);
    }

}
