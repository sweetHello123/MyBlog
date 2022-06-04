package com.it.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    @TableId(value = "id")
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 是否管理员
     */
    @TableField(value = "admin")
    private Boolean admin;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 注册时间
     */
    @TableField(value = "create_date")
    private Long createDate;

    /**
     * 是否删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_login")
    private Long lastLogin;

    /**
     * 手机号
     */
    @TableField(value = "mobile_phone_number")
    private String mobilePhoneNumber;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 加密盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 状态
     */
    @TableField(value = "status")
    private String status;

    private static final long serialVersionUID = 1L;
}