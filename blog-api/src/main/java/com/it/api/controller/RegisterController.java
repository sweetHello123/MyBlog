package com.it.api.controller;

import com.it.api.common.Result;
import com.it.api.service.LoginService;
import com.it.api.vo.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 20:40
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户注册(带登录)
     *
     * @param loginParam
     * @return
     */
    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }

}
