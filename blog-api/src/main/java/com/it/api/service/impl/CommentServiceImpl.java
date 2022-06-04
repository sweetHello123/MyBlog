package com.it.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.entity.Comment;
import com.it.api.entity.SysUser;
import com.it.api.mapper.CommentMapper;
import com.it.api.service.CommentService;
import com.it.api.service.SysUserService;
import com.it.api.utils.UserThreadLocal;
import com.it.api.vo.CommentVo;
import com.it.api.vo.UserVo;
import com.it.api.vo.param.CommentParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SysUserService userService;

    @Override
    public List<CommentVo> getCommentsByArticle(Long id) {
        // 根据文章id查询所有评论列表
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id)
                .eq(Comment::getLevel, 1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return convertToVo(comments);
    }

    @Override
    public List<CommentVo> getCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, id)
                .eq(Comment::getLevel, 2);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return convertToVo(comments);
    }

    @Override
    public void addComment(CommentParam commentParam) {
        // 获取系统当前用户
        SysUser user = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setAuthorId(user.getId());
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        comment.setToUid(commentParam.getToUserId());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel("1");
        } else {
            comment.setLevel("2");
        }
        comment.setParentId(parent == null ? 0L : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0L : toUserId);
        commentMapper.insert(comment);
    }

    private List<CommentVo> convertToVo(List<Comment> comments) {
        return comments.stream().map(this::convertToVo).collect(Collectors.toList());
    }

    private CommentVo convertToVo(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        // 时间格式化
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        // 评论人
        UserVo userVo = userService.getUserVoById(comment.getAuthorId());
        commentVo.setAuthor(userVo);
        // 子评论
        if (Integer.parseInt(comment.getLevel()) == 1) {
            // 查询子评论列表
            List<CommentVo> childrens = commentService.getCommentsByParentId(comment.getId());
            commentVo.setChildrens(childrens);
        }
        // 对谁评论
        if (Integer.parseInt(comment.getLevel()) > 1) {
            UserVo toUser = userService.getUserVoById(comment.getToUid());
            commentVo.setToUser(toUser);
        }
        return commentVo;
    }
}
