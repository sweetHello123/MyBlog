package com.it.admin.service.impl;

import com.it.admin.entity.Admin;
import com.it.admin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 21:00
 */
@Slf4j
@Component
public class SecurityUserServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}", username);
        // 当用户登录的时候，springSecurity就会将请求转发到此
        // 根据用户名查找用户，不存在 抛出异常
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            // 登录失败
            return null;
        }
        // 存在则将用户名、密码、授权列表组装成springSecurity的User对象并返回
        return new User(username, admin.getPassword(), new ArrayList<>());
    }
}
