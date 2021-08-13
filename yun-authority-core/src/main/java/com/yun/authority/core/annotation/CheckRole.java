package com.yun.authority.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     角色认证：必须具有指定角色标识才能进入该方法
 *     可标注在函数、类上（效果等同于标注在此类的所有方法上）
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 14:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface CheckRole {

    /**
     * 需要校验的角色标识
     * @return 需要校验的角色标识
     */
    String [] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     * @return 验证模式
     */
    CheckMode mode() default CheckMode.AND;

    /**
     * 账号类型
     * <p> 建议使用常量，避免因错误拼写带来的bug
     * @return see note
     */
    String type() default "";

}
