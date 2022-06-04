package com.it.api.controller;

import com.it.api.common.Result;
import com.it.api.entity.Tag;
import com.it.api.service.TagService;
import com.it.api.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 0:03
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 首页-热门标签
     *
     * @return
     */
    @GetMapping("/hot")
    public Result hotTag() {
        int limit = 6;
        List<Long> tagIds = tagService.getHotTag(limit);
        List<Tag> tags = tagService.listByIds(tagIds);
        return Result.success(tags);
    }

    /**
     * 文章发布-标签名称列表
     *
     * @return
     */
    @GetMapping
    public Result listTag() {
        List<TagVo> tagVos = tagService.getTagName();
        return Result.success(tagVos);
    }

    /**
     * 标签详情列表
     *
     * @return
     */
    @GetMapping("/detail")
    public Result getDetails() {
        List<TagVo> tagVos = tagService.getTagDetails();
        return Result.success(tagVos);
    }

    /**
     * 查看某个标签
     *
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result getDetailById(@PathVariable Long id) {
        TagVo tagVo = tagService.getTagDetailById(id);
        return Result.success(tagVo);
    }

}
