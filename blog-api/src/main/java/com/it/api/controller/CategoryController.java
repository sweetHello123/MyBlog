package com.it.api.controller;

import com.it.api.common.Result;
import com.it.api.service.CategoryService;
import com.it.api.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/15 23:25
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 文章发布-分类名称列表
     *
     * @return
     */
    @GetMapping
    public Result listCategory() {
        List<CategoryVo> categoryVos = categoryService.getCategoryName();
        return Result.success(categoryVos);
    }

    /**
     * 文章分类详情列表
     *
     * @return
     */
    @GetMapping("/detail")
    public Result getDetails() {
        List<CategoryVo> categoryVos = categoryService.getCategoryDetails();
        return Result.success(categoryVos);
    }

    /**
     * 查看某个文章分类
     *
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result getDetailById(@PathVariable long id) {
        CategoryVo categoryVo = categoryService.getCategoryDetailById(id);
        return Result.success(categoryVo);
    }

}
