package com.it.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.api.entity.Tag;
import com.it.api.vo.TagVo;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface TagService extends IService<Tag> {

    /**
     * 查询对应文章的所有标签
     *
     * @param articleId
     * @return
     */
    List<TagVo> getTagsByArticleId(Long articleId);

    /**
     * 查询拥有文章数量最多的热门标签
     *
     * @param limit
     * @return
     */
    List<Long> getHotTag(int limit);

    /**
     * 获取所有标签名称
     *
     * @return
     */
    List<TagVo> getTagName();

    /**
     * 查询所有标签的详细信息
     *
     * @return
     */
    List<TagVo> getTagDetails();

    /**
     * 根据id查询标签详情
     *
     * @param id
     * @return
     */
    TagVo getTagDetailById(Long id);

}
