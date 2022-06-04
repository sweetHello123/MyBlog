package com.it.api.controller;

import com.it.api.aop.LogAnnotation;
import com.it.api.common.PageParams;
import com.it.api.common.Result;
import com.it.api.dto.Archive;
import com.it.api.service.ArticleService;
import com.it.api.vo.ArticleVo;
import com.it.api.vo.param.ArticleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:16
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页-文章列表
     *
     * @param pageParams
     * @return
     */
    @PostMapping
    @Cacheable(cacheNames = "articleCache", keyGenerator = "keyGenerator")
    @LogAnnotation(module = "文章列表", operation = "获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams) {
        List<ArticleVo> articleVos = articleService.page(pageParams);
        return Result.success(articleVos);
    }

    /**
     * 首页-最热文章
     *
     * @return
     */
    @PostMapping("/hot")
    @Cacheable(cacheNames = "articleCache", keyGenerator = "keyGenerator")
    public Result hotArticle() {
        int limit = 5;
        List<ArticleVo> articleVos = articleService.getHotArticle(limit);
        return Result.success(articleVos);
    }

    /**
     * 首页-最新文章
     *
     * @return
     */
    @PostMapping("/new")
    @Cacheable(cacheNames = "articleCache", keyGenerator = "keyGenerator")
    public Result newArticle() {
        int limit = 5;
        List<ArticleVo> articleVos = articleService.getNewArticle(limit);
        return Result.success(articleVos);
    }

    /**
     * 首页-文章归档
     *
     * @return
     */
    @PostMapping("/listArchives")
    public Result listArchives() {
        List<Archive> archives = articleService.getArchive();
        return Result.success(archives);
    }

    /**
     * 查看文章详情
     *
     * @param id
     * @return
     */
    @PostMapping("/view/{id}")
    @CacheEvict(cacheNames = "articleCache", allEntries = true)
    public Result getArticle(@PathVariable Long id) {
        ArticleVo articleVo = articleService.getArticleById(id);
        return Result.success(articleVo);
    }

    /**
     * 发布文章
     *
     * @param articleParam
     * @return
     */
    @PostMapping("/publish")
    @CacheEvict(cacheNames = "articleCache", allEntries = true)
    public Result publishArticle(@RequestBody ArticleParam articleParam) {
        ArticleVo articleVo = articleService.addArticle(articleParam);
        return Result.success(articleVo);
    }

    /**
     * 编辑文章
     *
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    @CacheEvict(cacheNames = "articleCache", allEntries = true)
    public Result editArticle(@PathVariable Long id) {
        ArticleVo articleVo = articleService.getArticleById(id);
        return Result.success(articleVo);
    }

    /**
     * 搜索文章
     *
     * @param articleParam
     * @return
     */
    @PostMapping("/search")
    public Result searchArticle(@RequestBody ArticleParam articleParam) {
        List<ArticleVo> articleVos = articleService.getArticleBySearch(articleParam.getSearch());
        return Result.success(articleVos);
    }

}