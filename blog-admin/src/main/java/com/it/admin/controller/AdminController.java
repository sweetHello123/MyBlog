package com.it.admin.controller;

import com.it.admin.entity.Permission;
import com.it.admin.params.PageParam;
import com.it.admin.service.PermissionService;
import com.it.admin.vo.PageResult;
import com.it.admin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:40
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission/permissionList")
    public Result listPermission(@RequestBody PageParam pageParam) {
        PageResult<Permission> pageResult = permissionService.pagePermission(pageParam);
        return Result.success(pageResult);
    }

    @PostMapping("/permission/add")
    public Result add(@RequestBody Permission permission){
        permissionService.save(permission);
        return Result.success(null);
    }

    @PostMapping("/permission/update")
    public Result update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return Result.success(null);
    }

    @GetMapping("/permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        permissionService.removeById(id);
        return Result.success(null);
    }

}
