package com.it.api.controller;

import com.it.api.common.Result;
import com.it.api.service.LoginService;
import com.it.api.vo.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 18:04
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }

    /**
     * 退出
     *
     * @param token
     * @return
     */
    @GetMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }
}
