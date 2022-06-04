package com.it.api.vo.param;

import lombok.Data;

/**
 * @Author: china wu
 * @Description: 评论参数封装
 * @Date: 2022/5/15 20:53
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;

}
