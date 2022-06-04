package com.it.api.common;

import lombok.Data;

/**
 * @Author: china wu
 * @Description: 分页字段包装
 * @Date: 2022/5/13 22:42
 */
@Data
public class PageParams {

    /**
     * 页码
     */
    private int page;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 年份
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    public String getMonth() {
        // 1-9月数字前加0
        if (this.month != null && this.month.length() == 1) {
            return "0" + this.month;
        }
        return this.month;
    }

}
