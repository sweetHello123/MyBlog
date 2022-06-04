package com.it.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.it.api.entity.Category;
import com.it.api.vo.CategoryVo;

import java.util.List;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 21:11
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据id查询分类
     *
     * @param categoryId
     * @return
     */
    CategoryVo getCategoryById(Long categoryId);

    /**
     * 获取所有文章分类名称
     *
     * @return
     */
    List<CategoryVo> getCategoryName();

    /**
     * 查询所有文章分类的详细信息
     *
     * @return
     */
    List<CategoryVo> getCategoryDetails();

    /**
     * 根据id查询文章分类详情
     *
     * @param id
     * @return
     */
    CategoryVo getCategoryDetailById(long id);

}
