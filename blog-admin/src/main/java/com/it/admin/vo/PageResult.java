package com.it.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: china wu
 * @Description: 分页结果集
 * @Date: 2022/5/17 19:32
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;

}
