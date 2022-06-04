package com.it.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.admin.params.PageParam;
import com.it.admin.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.admin.mapper.PermissionMapper;
import com.it.admin.entity.Permission;
import com.it.admin.service.PermissionService;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:47
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageResult<Permission> pagePermission(PageParam pageParam) {
        Page<Permission> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(pageParam.getQueryString()), Permission::getName, pageParam.getQueryString());
        Page<Permission> permissionPage = permissionMapper.selectPage(page, queryWrapper);
        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(permissionPage.getRecords());
        pageResult.setTotal(permissionPage.getTotal());
        return pageResult;
    }

    @Override
    public List<Permission> getPermissionByAdminId(Long id) {
        return permissionMapper.getAdminPermission(id);
    }

}
