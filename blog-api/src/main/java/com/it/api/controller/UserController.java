package com.it.api.controller;

import com.it.api.common.ErrorCode;
import com.it.api.common.Result;
import com.it.api.service.SysUserService;
import com.it.api.vo.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 19:20
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SysUserService userService;

    /**
     * 获取当前用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        LoginUserVo userVo = userService.getUserByToken(token);
        if (userVo == null) {
            return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
        }
        return Result.success(userVo);
    }
}
