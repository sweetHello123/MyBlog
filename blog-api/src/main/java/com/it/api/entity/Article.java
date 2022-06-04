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
@TableName(value = "article")
public class Article implements Serializable {

    @TableId(value = "id")
    private Long id;

    /**
     * 评论数量
     */
    @TableField(value = "comment_counts")
    private Integer commentCounts;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    private Long createDate;

    /**
     * 简介
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 浏览数量
     */
    @TableField(value = "view_counts")
    private Integer viewCounts;

    /**
     * 是否置顶
     */
    @TableField(value = "weight")
    private Integer weight;

    /**
     * 作者id
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 内容id
     */
    @TableField(value = "body_id")
    private Long bodyId;

    /**
     * 类别id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    private static final long serialVersionUID = 1L;

}