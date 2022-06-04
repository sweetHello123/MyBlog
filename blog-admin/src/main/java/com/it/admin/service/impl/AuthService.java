package com.it.admin.service.impl;

import com.it.admin.entity.Admin;
import com.it.admin.entity.Permission;
import com.it.admin.service.AdminService;
import com.it.admin.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 21:17
 */
@Service
public class AuthService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PermissionService permissionService;

    public boolean auth(HttpServletRequest request, Authentication authentication) {
        // 获取用户请求路径
        String requestURI = request.getRequestURI();
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)) {
            // 未登录
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            return false;
        }
        // 超级管理员直接放行
        if (admin.getId() == 1) {
            return true;
        }
        // 查询该管理员的所有权限
        List<Permission> permissionList = permissionService.getPermissionByAdminId(admin.getId());
        // uri去掉?后的参数
        requestURI = StringUtils.split(requestURI, '?')[0];
        for (Permission permission : permissionList) {
            // 比较访问路径与权限路径值是否一致，相同则放行
            if (requestURI.equals(permission.getPath())) {
                return true;
            }
        }
        return false;
    }

}
