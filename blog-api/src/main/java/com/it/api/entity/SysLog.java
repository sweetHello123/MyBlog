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
@TableName(value = "sys_log")
public class SysLog implements Serializable {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "create_date")
    private Long createDate;

    @TableField(value = "ip")
    private String ip;

    @TableField(value = "method")
    private String method;

    @TableField(value = "module")
    private String module;

    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "operation")
    private String operation;

    @TableField(value = "params")
    private String params;

    @TableField(value = "time")
    private Long time;

    @TableField(value = "userid")
    private Long userid;

    private static final long serialVersionUID = 1L;
}