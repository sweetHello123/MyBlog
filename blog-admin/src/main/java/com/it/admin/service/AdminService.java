package com.it.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.admin.entity.Admin;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:47
 */
public interface AdminService extends IService<Admin> {

    /**
     * 根据用户名查询管理员
     *
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);

}
