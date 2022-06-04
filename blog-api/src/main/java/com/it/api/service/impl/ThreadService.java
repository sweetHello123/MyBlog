package com.it.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.it.api.entity.Article;
import com.it.api.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: china wu
 * @Description: 多线程任务
 * @Date: 2022/5/15 17:41
 */
@Slf4j
@Component
public class ThreadService {

    /**
     * 更新文章阅读数
     *
     * @param articleMapper
     * @param article
     */
    @Async("taskExecutor")
    void updateArticleViewCounts(ArticleMapper articleMapper, Article article) {
        log.info("viewCounts:{}", article.getViewCounts());
        // 增加阅读数
        Article art = new Article();
        art.setViewCounts(article.getViewCounts() + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, article.getId())
                .eq(Article::getViewCounts, article.getViewCounts());
        articleMapper.update(art, queryWrapper);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
