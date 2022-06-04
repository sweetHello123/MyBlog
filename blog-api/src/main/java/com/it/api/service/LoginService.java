package com.it.api.service;

import com.it.api.common.Result;
import com.it.api.entity.SysUser;
import com.it.api.vo.param.LoginParam;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 18:05
 */
public interface LoginService {

    /**
     * 用户登录
     *
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 用户注销
     *
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 用户注册
     *
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);

    /**
     * token校验
     *
     * @param token
     * @return
     */
    SysUser checkToken(String token);

}
