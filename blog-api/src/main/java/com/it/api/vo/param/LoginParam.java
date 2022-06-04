package com.it.api.vo.param;

import lombok.Data;

/**
 * @Author: china wu
 * @Description: 登录参数封装
 * @Date: 2022/5/14 18:08
 */
@Data
public class LoginParam {

    private String account;

    private String password;

    private String nickname;

}
