package com.it.api.utils;

import com.it.api.entity.SysUser;

/**
 * @Author: china wu
 * @Description: 用户本地线程工具类
 * @Date: 2022/5/14 22:18
 */
public class UserThreadLocal {

    private UserThreadLocal() {
    }

    /**
     * ThreadLocal用于线程变量隔离
     */
    private static final ThreadLocal<SysUser> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(SysUser user) {
        THREAD_LOCAL.set(user);
    }

    public static SysUser get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
