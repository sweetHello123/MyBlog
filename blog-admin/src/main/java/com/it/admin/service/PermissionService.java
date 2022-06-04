package com.it.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.admin.entity.Permission;
import com.it.admin.params.PageParam;
import com.it.admin.vo.PageResult;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:47
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 分页查询权限表数据
     *
     * @param pageParam 分页参数
     * @return
     */
    PageResult<Permission> pagePermission(PageParam pageParam);

    /**
     * 根据管理员id查询权限
     *
     * @param id
     * @return
     */
    List<Permission> getPermissionByAdminId(Long id);

}
