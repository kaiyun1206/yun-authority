package com.yun.authority.core.exception;

import com.yun.authority.core.auth.AuthUtil;
import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     会话未能通过角色认证
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 19:58
 */
public class NotRoleException extends BaseException {
    private static final long serialVersionUID = -5555220547634758714L;

    /** 角色标识 */
    private String role;

    /**
     * @return 获得角色标识
     */
    public String getRole() {
        return role;
    }

    /**
     * 账号类型
     */
    private String loginType;

    /**
     * 获得账号类型
     *
     * @return 账号类型
     */
    public String getLoginType() {
        return loginType;
    }

    public NotRoleException(String role) {
        this(role, AuthUtil.authLogic.loginType);
    }

    public NotRoleException(String role, String loginType) {
        super("无此角色：" + role);
        this.role = role;
        this.loginType = loginType;
    }
}
