package com.yun.authority.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     二级认证校验：必须二级认证之后才能进入该方法
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 14:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface CheckSafe {
}
