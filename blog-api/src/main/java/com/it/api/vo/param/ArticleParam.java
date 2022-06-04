package com.it.api.vo.param;

import com.it.api.vo.CategoryVo;
import com.it.api.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @Author: china wu
 * @Description: 文章参数封装
 * @Date: 2022/5/16 0:22
 */
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;

    private String search;

}

