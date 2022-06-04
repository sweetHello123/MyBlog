package com.it.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.entity.Tag;
import com.it.api.mapper.TagMapper;
import com.it.api.service.TagService;
import com.it.api.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVo> getTagsByArticleId(Long articleId) {
        List<Tag> tags = tagMapper.getByArticleId(articleId);
        return convertToVo(tags);
    }

    @Override
    public List<Long> getHotTag(int limit) {
        return tagMapper.selectHotTag(limit);
    }

    @Override
    public List<TagVo> getTagName() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        return convertToVo(tags);
    }

    @Override
    public List<TagVo> getTagDetails() {
        List<Tag> tags = this.list();
        return convertToVo(tags);
    }

    @Override
    public TagVo getTagDetailById(Long id) {
        Tag tag = this.getById(id);
        return convertToVo(tag);
    }

    /**
     * 转换成标签视图对象
     *
     * @param tags 标签数据对象集合
     * @return
     */
    private List<TagVo> convertToVo(List<Tag> tags) {
        return tags.stream().map(this::convertToVo).collect(Collectors.toList());
    }

    /**
     * 转换成标签视图对象集合
     *
     * @param tag 单个标签数据对象
     * @return
     */
    private TagVo convertToVo(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }

}
