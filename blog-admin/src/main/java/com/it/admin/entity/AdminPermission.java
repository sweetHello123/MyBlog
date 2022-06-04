package com.it.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:47
 */
@Data
@TableName(value = "admin_permission")
public class AdminPermission implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "admin_id")
    private Long adminId;

    @TableField(value = "permission_id")
    private Long permissionId;

    private static final long serialVersionUID = 1L;

}