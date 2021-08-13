package com.yun.authority.core.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     对 {@link AuthInterface} 接口默认的实现类
 *     如果开发者没有实现AuthInterface接口，则使用此默认实现
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-09 15:04
 */
public class AuthInterfaceDefaultImpl implements AuthInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return new ArrayList<>();
    }
}
