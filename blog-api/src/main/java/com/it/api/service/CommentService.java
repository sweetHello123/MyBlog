package com.it.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.api.entity.Comment;
import com.it.api.vo.CommentVo;
import com.it.api.vo.param.CommentParam;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface CommentService extends IService<Comment> {

    /**
     * 查询文章所有的评论信息
     *
     * @param id
     * @return
     */
    List<CommentVo> getCommentsByArticle(Long id);

    /**
     * 查询子评论
     *
     * @param id
     * @return
     */
    List<CommentVo> getCommentsByParentId(Long id);

    /**
     * 发布评论
     *
     * @param commentParam
     * @return
     */
    void addComment(CommentParam commentParam);

}
