package com.it.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.common.PageParams;
import com.it.api.dto.Archive;
import com.it.api.entity.Article;
import com.it.api.entity.ArticleBody;
import com.it.api.entity.ArticleTag;
import com.it.api.entity.SysUser;
import com.it.api.mapper.ArticleMapper;
import com.it.api.service.*;
import com.it.api.utils.UserThreadLocal;
import com.it.api.vo.*;
import com.it.api.vo.param.ArticleBodyParam;
import com.it.api.vo.param.ArticleParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArticleBodyService articleBodyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ThreadService threadService;

    @Override
    public List<ArticleVo> page(PageParams pageParams) {
        Page<Article> pageInfo = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        Page<Article> Page = articleMapper.listArticle(pageInfo, pageParams.getCategoryId(), pageParams.getTagId(),
                pageParams.getYear(), pageParams.getMonth());
        List<Article> records = Page.getRecords();
        return convertToVo(records, true, true, true, true);
    }

    @Override
    public List<ArticleVo> getHotArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts)
                .select(Article::getId, Article::getTitle)
                .last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return convertToVo(articles, false, false, false, false);
    }

    @Override
    public List<ArticleVo> getNewArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getId, Article::getTitle);
        queryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return convertToVo(articles, false, false, false, false);
    }

    @Override
    public List<Archive> getArchive() {
        return articleMapper.listArchives();
    }

    @Override
    public ArticleVo getArticleById(Long id) {
        // ??????????????????
        Article article = articleMapper.selectById(id);
        // ?????????????????????
        threadService.updateArticleViewCounts(articleMapper, article);
        return convertToVo(article, true, true, true, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleVo addArticle(ArticleParam articleParam) {
        // ??????????????????
        SysUser user = UserThreadLocal.get();
        // ???????????????
        boolean isEdit = false;
        Article article = new Article();
        if (articleParam.getId() != null) {
            // ????????????
            article.setId(articleParam.getId());
            article.setTitle(articleParam.getTitle());
            article.setSummary(articleParam.getSummary());
            article.setCategoryId(articleParam.getCategory().getId());
            this.updateById(article);
            isEdit = true;
        } else {
            // ????????????
            article.setAuthorId(user.getId());
            article.setWeight(0);
            article.setViewCounts(0);
            article.setCommentCounts(0);
            article.setTitle(articleParam.getTitle());
            article.setSummary(articleParam.getSummary());
            article.setCreateDate(System.currentTimeMillis());
            article.setCategoryId(articleParam.getCategory().getId());
            this.save(article);
        }
        // ???????????????????????????
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tagVo : tags) {
                if (isEdit) {
                    // ??????????????????????????????????????????
                    LambdaQueryWrapper<ArticleTag> wrapper = Wrappers.lambdaQuery();
                    wrapper.eq(ArticleTag::getArticleId, article.getId());
                    articleTagService.remove(wrapper);
                }
                // ??????????????????????????????
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(tagVo.getId());
                articleTagService.save(articleTag);
            }
        }
        ArticleBody articleBody = new ArticleBody();
        // ??????????????????
        ArticleBodyParam body = articleParam.getBody();
        if (isEdit) {
            // ?????????????????????
            LambdaUpdateWrapper<ArticleBody> wrapper = Wrappers.lambdaUpdate();
            articleBody.setArticleId(article.getId());
            articleBody.setContent(body.getContent());
            articleBody.setContentHtml(body.getContentHtml());
            wrapper.eq(ArticleBody::getArticleId, article.getId());
            articleBodyService.update(articleBody, wrapper);
        } else {
            // ??????????????????
            articleBody.setArticleId(article.getId());
            articleBody.setContent(body.getContent());
            articleBody.setContentHtml(body.getContentHtml());
            articleBodyService.save(articleBody);
            // ??????????????????body_id???????????????
            article.setBodyId(articleBody.getId());
            this.updateById(article);
        }
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());
        return articleVo;
    }

    @Override
    public List<ArticleVo> getArticleBySearch(String search) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Article::getTitle, search)
                .select(Article::getId, Article::getTitle)
                .orderByDesc(Article::getViewCounts);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return convertToVo(articles, false, false, false, false);
    }

    /**
     * ?????????????????????????????????
     *
     * @param articles   ????????????????????????
     * @param isTag      ??????????????????
     * @param isAuthor   ??????????????????
     * @param isBody     ??????????????????
     * @param isCategory ??????????????????
     * @return
     */
    private List<ArticleVo> convertToVo(List<Article> articles,
                                        boolean isTag,
                                        boolean isAuthor,
                                        boolean isBody,
                                        boolean isCategory) {
        return articles.stream().map(article -> convertToVo(article, isTag, isAuthor, isBody, isCategory))
                .collect(Collectors.toList());
    }

    /**
     * ???????????????????????????
     *
     * @param article    ??????????????????
     * @param isTag      ??????????????????
     * @param isAuthor   ??????????????????
     * @param isBody     ??????????????????
     * @param isCategory ??????????????????
     * @return
     */
    private ArticleVo convertToVo(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (isTag) {
            // ??????????????????
            List<TagVo> tagVos = tagService.getTagsByArticleId(article.getId());
            articleVo.setTags(tagVos);
        }
        if (isAuthor) {
            // ??????????????????
            SysUser sysUser = sysUserService.getById(article.getAuthorId());
            if (sysUser != null) {
                UserVo userVo = new UserVo();
                userVo.setId(sysUser.getId());
                userVo.setAvatar(sysUser.getAvatar());
                userVo.setNickname(sysUser.getNickname());
                articleVo.setAuthor(userVo);
            }
        }
        if (isBody) {
            ArticleBodyVo articleBodyVo = articleBodyService.getArticleBodyByArticleId(article.getId());
            articleVo.setBody(articleBodyVo);
        }
        if (isCategory) {
            CategoryVo categoryVo = categoryService.getCategoryById(article.getCategoryId().longValue());
            articleVo.setCategory(categoryVo);
        }
        return articleVo;
    }

}
