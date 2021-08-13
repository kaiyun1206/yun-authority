package com.yun.authority.core.auth;

import java.util.List;

/**
 * <p>
 *     权限认证接口，实现此接口即可集成权限认证功能
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 15:00
 */
public interface AuthInterface {

    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId  账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    public List<String> getPermissionList(Object loginId, String loginType);

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId  账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    public List<String> getRoleList(Object loginId, String loginType);
}
