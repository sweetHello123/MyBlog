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
@TableName(value = "tag")
public class Tag implements Serializable {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "tag_name")
    private String tagName;

    private static final long serialVersionUID = 1L;

}