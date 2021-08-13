package com.yun.authority.spring;

import com.yun.authority.core.exception.AuthTokenException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *     SpringMVC相关操作
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-12 14:39
 */
public class SpringMVCUtil {

    /**
     * 获取当前会话的 request
     * @return request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null) {
            throw new AuthTokenException("非Web上下文无法获取Request");
        }
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取当前会话的 response
     * @return response
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null) {
            throw new AuthTokenException("非Web上下文无法获取Response");
        }
        return servletRequestAttributes.getResponse();
    }
}
