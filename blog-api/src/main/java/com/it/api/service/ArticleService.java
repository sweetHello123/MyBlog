package com.it.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.api.common.PageParams;
import com.it.api.dto.Archive;
import com.it.api.entity.Article;
import com.it.api.vo.ArticleVo;
import com.it.api.vo.param.ArticleParam;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页显示文章
     *
     * @param pageParams
     * @return
     */
    List<ArticleVo> page(PageParams pageParams);

    /**
     * 查询访问量最多的前几篇文章
     *
     * @param limit 前条数
     * @return
     */
    List<ArticleVo> getHotArticle(int limit);

    /**
     * 查询最新发布的前几篇文章
     *
     * @param limit 前条数
     * @return
     */
    List<ArticleVo> getNewArticle(int limit);

    /**
     * 查询文章归档信息
     *
     * @return
     */
    List<Archive> getArchive();

    /**
     * 查询文章详情
     *
     * @param id
     * @return
     */
    ArticleVo getArticleById(Long id);

    /**
     * 发布文章
     *
     * @param articleParam
     * @return
     */
    ArticleVo addArticle(ArticleParam articleParam);


    /**
     * 搜索文章
     *
     * @param search
     * @return
     */
    List<ArticleVo> getArticleBySearch(String search);

}
