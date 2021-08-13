package com.yun.authority.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     权限认证：必须具有指定权限才能进入该方法
 *     可标注在函数、类上（效果等同于标注在此类的所有方法上）
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 14:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface CheckPermission {

    /**
     * 需要校验的权限码
     * @return 需要校验的权限码
     */
    String [] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     * @return 验证模式
     */
    CheckMode mode() default CheckMode.AND;

    /**
     * 多账号体系下所属的账号体系标识
     * @return see note
     */
    String type() default "";

}
