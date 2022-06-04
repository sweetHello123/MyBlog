package com.it.api.controller;

import com.it.api.common.Result;
import com.it.api.service.CommentService;
import com.it.api.vo.CommentVo;
import com.it.api.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/15 18:32
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论列表
     *
     * @param id
     * @return
     */
    @GetMapping("/article/{id}")
    public Result listComment(@PathVariable Long id) {
        List<CommentVo> commentVos = commentService.getCommentsByArticle(id);
        return Result.success(commentVos);
    }

    /**
     * 发布评论
     *
     * @param commentParam
     * @return
     */
    @PostMapping("/publish")
    public Result publishComment(@RequestBody CommentParam commentParam) {
        commentService.addComment(commentParam);
        return Result.success(null);
    }

}
