package com.it.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Data
@TableName(value = "article_body")
public class ArticleBody implements Serializable {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "content")
    private String content;

    @TableField(value = "content_html")
    private String contentHtml;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "article_id")
    private Long articleId;

    private static final long serialVersionUID = 1L;
}