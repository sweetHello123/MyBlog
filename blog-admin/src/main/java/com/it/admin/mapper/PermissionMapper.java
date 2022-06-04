package com.it.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it.admin.entity.Permission;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:47
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询某管理员的所有权限
     *
     * @param id
     * @return
     */
    List<Permission> getAdminPermission(Long id);

}