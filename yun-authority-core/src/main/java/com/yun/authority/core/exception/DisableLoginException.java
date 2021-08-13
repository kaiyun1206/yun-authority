package com.yun.authority.core.exception;

import com.yun.footstone.common.exception.BaseException;

/**
 * <p>
 *     账号已被封禁
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 19:53
 */
public class DisableLoginException extends BaseException {
    private static final long serialVersionUID = 6361868156886356433L;

    /** 异常标记值 */
    public static final String BE_VALUE = "disable";

    /** 异常提示语 */
    public static final String BE_MESSAGE = "此账号已被封禁";

    /**
     * 账号类型
     */
    private String loginType;

    /**
     * 被封禁的账号id
     */
    private Object loginId;

    /**
     * 封禁剩余时间，单位：秒
     */
    private long disableTime;

    /**
     * 获取账号类型
     *
     * @return See Note
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * 获取: 被封禁的账号id
     *
     * @return See above
     */
    public Object getLoginId() {
        return loginId;
    }

    /**
     * 获取: 封禁剩余时间，单位：秒
     * @return See above
     */
    public long getDisableTime() {
        return disableTime;
    }

    /**
     * 一个异常：代表账号已被封禁
     *
     * @param loginType 账号类型
     * @param loginId  被封禁的账号id
     * @param disableTime 封禁剩余时间，单位：秒
     */
    public DisableLoginException(String loginType, Object loginId, long disableTime) {
        super(BE_MESSAGE);
        this.loginId = loginId;
        this.loginType = loginType;
        this.disableTime = disableTime;
    }
}
