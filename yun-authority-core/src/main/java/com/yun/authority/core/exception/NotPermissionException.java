package com.yun.authority.core.exception;

import com.yun.authority.core.auth.AuthUtil;
import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     会话未能通过权限认证
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 17:12
 */
public class NotPermissionException extends BaseException {

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

    public NotPermissionException(String permissionCode) {
        this(permissionCode, AuthUtil.authLogic.loginType);
    }

    public NotPermissionException(String permissionCode, String loginType) {
        super("无此权限：" + permissionCode);
        this.loginType = loginType;
    }

}
