package com.it.admin.params;

import lombok.Data;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/17 8:41
 */
@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;

}
