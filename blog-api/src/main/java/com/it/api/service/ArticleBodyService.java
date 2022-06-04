package com.it.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.api.entity.ArticleBody;
import com.it.api.vo.ArticleBodyVo;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface ArticleBodyService extends IService<ArticleBody> {

    /**
     * 根据文章id查询文体信息
     *
     * @param articleId
     * @return
     */
    ArticleBodyVo getArticleBodyByArticleId(Long articleId);

}
