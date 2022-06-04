package com.it.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.it.api.entity.Tag;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询对应标签
     *
     * @param id 文章id
     * @return
     */
    List<Tag> getByArticleId(Long id);

    /**
     * 根据tag_id排序，找出对应文章数最多的前几条
     *
     * @param limit 前条数
     * @return
     */
    List<Long> selectHotTag(int limit);

}