package com.yun.authority.core.context.model;

/**
 * <p>
 *     [存储器] 包装类
 *     在 Request作用域里: 存值、取值
 * </p>
 *
 * @author sunkaiyun
 * @version 1.0
 * @date 2021-08-10 20:10
 */
public interface AuthStorage {

    /**
     * 获取底层源对象
     * @return see note
     */
    Object getSource();

    /**
     * 在 [Request作用域] 里写入一个值
     * @param key 键
     * @param value 值
     */
    void set(String key, Object value);

    /**
     * 在 [Request作用域] 里获取一个值
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 在 [Request作用域] 里删除一个值
     * @param key 键
     */
    void delete(String key);

}
