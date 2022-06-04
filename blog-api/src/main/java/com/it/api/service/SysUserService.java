package com.it.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.api.entity.SysUser;
import com.it.api.vo.LoginUserVo;
import com.it.api.vo.UserVo;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据账户、密码查询用户
     *
     * @param account  账户
     * @param password 密码
     * @return
     */
    SysUser getUser(String account, String password);

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @return
     */
    LoginUserVo getUserByToken(String token);

    /**
     * 根据账户查询用户
     *
     * @param account
     * @return
     */
    SysUser getUserByAccount(String account);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    UserVo getUserVoById(Long id);
}
