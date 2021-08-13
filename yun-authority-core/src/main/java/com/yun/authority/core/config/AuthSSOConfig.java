package com.yun.authority.core.config;

import com.yun.authority.core.exception.AuthTokenException;
import com.yun.footstone.common.domain.CommonResponse;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * <p>
 *     Auth-Token-SSO 单点登录模块 配置Model
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-08 22:50
 */
public class AuthSSOConfig implements Serializable {
    private static final long serialVersionUID = 5873651098488608308L;

    /**
     * Ticket有效期 (单位: 秒)
     */
    public long ticketTimeout = 60 * 5;

    /**
     * 所有允许的授权回调地址，多个用逗号隔开 (不在此列表中的URL将禁止下放ticket) 
     */
    public String allowUrl = "*";

    /**
     * 接口调用秘钥 (用于SSO模式单点注销的接口通信身份校验)
     */
    public String secretKey;

    /**
     * SSO-Server端 单点登录地址
     */
    public String authUrl;

    /**
     * SSO-Server端 Ticket校验地址
     */
    public String checkTicketUrl;

    /**
     * SSO-Server端 单点注销地址 
     */
    public String sloUrl;

    /**
     * SSO-Client端 当前Client端的单点注销回调URL （为空时自动获取） 
     */
    public String ssoLogoutCall;

    /**
     * SSO-Server端 账号资料查询地址 
     */
    public String userinfoUrl;



    /**
     * @return Ticket有效期 (单位: 秒) 
     */
    public long getTicketTimeout() {
        return ticketTimeout;
    }

    /**
     * @param ticketTimeout Ticket有效期 (单位: 秒) 
     * @return 对象自身
     */
    public AuthSSOConfig setTicketTimeout(long ticketTimeout) {
        this.ticketTimeout = ticketTimeout;
        return this;
    }

    /**
     * @return 所有允许的授权回调地址，多个用逗号隔开 (不在此列表中的URL将禁止下放ticket) 
     */
    public String getAllowUrl() {
        return allowUrl;
    }

    /**
     * @param allowUrl 所有允许的授权回调地址，多个用逗号隔开 (不在此列表中的URL将禁止下放ticket) 
     * @return 对象自身
     */
    public AuthSSOConfig setAllowUrl(String allowUrl) {
        this.allowUrl = allowUrl;
        return this;
    }

    /**
     * @return 调用秘钥 (用于SSO模式三单点注销的接口通信身份校验)
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * @param secretKey 调用秘钥 (用于SSO模式单点注销的接口通信身份校验)
     * @return 对象自身
     */
    public AuthSSOConfig setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * @return SSO-Server端 单点登录地址
     */
    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * @param authUrl SSO-Server端 单点登录地址
     * @return 对象自身
     */
    public AuthSSOConfig setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
        return this;
    }

    /**
     * @return SSO-Server端Ticket校验地址 
     */
    public String getCheckTicketUrl() {
        return checkTicketUrl;
    }

    /**
     * @param checkTicketUrl SSO-Server端Ticket校验地址
     */
    public AuthSSOConfig setCheckTicketUrl(String checkTicketUrl) {
        this.checkTicketUrl = checkTicketUrl;
        return this;
    }

    /**
     * @return SSO-Server端单点注销地址
     */
    public String getSloUrl() {
        return sloUrl;
    }

    /**
     * @param sloUrl SSO-Server端单点注销地址
     * @return 对象自身
     */
    public AuthSSOConfig setSloUrl(String sloUrl) {
        this.sloUrl = sloUrl;
        return this;
    }

    /**
     * @return SSO-Client端 当前Client端的单点注销回调URL （为空时自动获取） 
     */
    public String getSsoLogoutCall() {
        return ssoLogoutCall;
    }

    /**
     * @param ssoLogoutCall SSO-Client端 当前Client端的单点注销回调URL （为空时自动获取） 
     * @return 对象自身
     */
    public AuthSSOConfig setSsoLogoutCall(String ssoLogoutCall) {
        this.ssoLogoutCall = ssoLogoutCall;
        return this;
    }

    /**
     * @return SSO-Server端 账号资料查询地址 
     */
    public String getUserinfoUrl() {
        return userinfoUrl;
    }

    /**
     * @param userinfoUrl SSO-Server端 账号资料查询地址 
     * @return 对象自身
     */
    public AuthSSOConfig setUserinfoUrl(String userinfoUrl) {
        this.userinfoUrl = userinfoUrl;
        return this;
    }

    @Override
    public String toString() {
        return "AuthSSOConfig [ticketTimeout=" + ticketTimeout + ", allowUrl=" + allowUrl + ", secretKey=" + secretKey
                + ", authUrl=" + authUrl + ", checkTicketUrl=" + checkTicketUrl + ", sloUrl=" + sloUrl
                + ", ssoLogoutCall=" + ssoLogoutCall + ", userinfoUrl=" + userinfoUrl + ", isHttp=" + isHttp + ", isSlo=" + isSlo + "]";
    }


    /**
     * 以数组形式写入允许的授权回调地址 
     * @param url 所有集合 
     * @return 对象自身
     */
    public AuthSSOConfig setAllow(String ...url) {
        if (null == url || url.length == 0) {
            this.allowUrl = "";
        } else {
            this.allowUrl = Arrays.stream(url).collect(Collectors.joining(","));
        }
        return this;
    }

    // -------------------- AuthSSOHandle 相关配置 --------------------

    /**
     * 是否使用http请求校验ticket值 
     */
    public Boolean isHttp = false;

    /**
     * 是否打开单点注销 
     */
    public Boolean isSlo = false;


    /**
     * @return isHttp 是否使用http请求校验ticket值 
     */
    public Boolean getIsHttp() {
        return isHttp;
    }

    /**
     * @param isHttp 是否使用http请求校验ticket值 
     * @return 对象自身
     */
    public AuthSSOConfig setIsHttp(Boolean isHttp) {
        this.isHttp = isHttp;
        return this;
    }

    /**
     * @return 是否打开单点注销
     */
    public Boolean getIsSlo() {
        return isSlo;
    }

    /**
     * @param isSlo 是否打开单点注销 
     * @return 对象自身
     */
    public AuthSSOConfig setIsSlo(Boolean isSlo) {
        this.isSlo = isSlo;
        return this;
    }


    // -------------------- AuthSSOHandle 所有回调函数 --------------------


    /**
     * SSO-Server端：未登录时返回的View 
     */
    public Supplier<Object> notLoginView = () -> "当前会话在SSO-Server认证中心尚未登录";

    /**
     * SSO-Server端：登录函数 
     */
    public BiFunction<String, String, Object> doLoginHandle = (name, pwd) -> CommonResponse.responseFail();

    /**
     * SSO-Client端：自定义校验Ticket返回值的处理逻辑 （每次从认证中心获取校验Ticket的结果后调用）
     */
    public BiFunction<Object, String, Object> ticketResultHandle = null;

    /**
     * SSO-Client端：发送Http请求的处理函数 
     */
    public Function<String, Object> sendHttp = url -> {throw new AuthTokenException("请配置Http处理器");};


    /**
     * @param notLoginView SSO-Server端：未登录时返回的View 
     * @return 对象自身
     */
    public AuthSSOConfig setNotLoginView(Supplier<Object> notLoginView) {
        this.notLoginView = notLoginView;
        return this;
    }

    /**
     * @param doLoginHandle SSO-Server端：登录函数 
     * @return 对象自身
     */
    public AuthSSOConfig setDoLoginHandle(BiFunction<String, String, Object> doLoginHandle) {
        this.doLoginHandle = doLoginHandle;
        return this;
    }

    /**
     * @param ticketResultHandle SSO-Client端：自定义校验Ticket返回值的处理逻辑 （每次从认证中心获取校验Ticket的结果后调用）
     * @return 对象自身
     */
    public AuthSSOConfig setTicketResultHandle(BiFunction<Object, String, Object> ticketResultHandle) {
        this.ticketResultHandle = ticketResultHandle;
        return this;
    }

    /**
     * @param sendHttp SSO-Client端：发送Http请求的处理函数 
     * @return 对象自身
     */
    public AuthSSOConfig setSendHttp(Function<String, Object> sendHttp) {
        this.sendHttp = sendHttp;
        return this;
    }
}
