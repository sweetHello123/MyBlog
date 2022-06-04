package com.it.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.api.entity.Category;
import com.it.api.mapper.CategoryMapper;
import com.it.api.service.CategoryService;
import com.it.api.vo.CategoryVo;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo getCategoryById(Long categoryId) {
        Category category = this.getById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }

    @Override
    public List<CategoryVo> getCategoryName() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId, Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return convertToVo(categories);
    }

    @Override
    public List<CategoryVo> getCategoryDetails() {
        List<Category> categories = this.list();
        return convertToVo(categories);
    }

    @Override
    public CategoryVo getCategoryDetailById(long id) {
        Category category = this.getById(id);
        return convertToVo(category);
    }

    private List<CategoryVo> convertToVo(List<Category> categories) {
        return categories.stream().map(this::convertToVo).collect(Collectors.toList());
    }

    private CategoryVo convertToVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
