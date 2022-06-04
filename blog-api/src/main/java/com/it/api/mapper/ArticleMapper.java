package com.it.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.api.dto.Archive;
import com.it.api.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 文章归档查询 - 查询某年某月发布的文章数量
     *
     * @return
     */
    List<Archive> listArchives();

    /**
     * 根据条件显示文章
     *
     * @param pageInfo   文章分页
     * @param categoryId 文章分类id
     * @param tagId      标签id
     * @param year       年份
     * @param month      月份
     * @return
     */
    Page<Article> listArticle(Page<Article> pageInfo, @Param("categoryId") Long categoryId, @Param("tagId") Long tagId,
                              @Param("year") String year, @Param("month") String month);

}