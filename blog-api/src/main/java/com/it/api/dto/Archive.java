package com.it.api.dto;

import lombok.Data;

/**
 * @Author: china wu
 * @Description: 文章归档-无需持久化
 * @Date: 2022/5/14 16:57
 */
@Data
public class Archive {

    private Integer year;

    private Integer month;

    private Integer count;
}
