package com.it.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.entity.ArticleTag;
import com.it.api.mapper.ArticleTagMapper;
import com.it.api.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService{
}
