package com.it.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/14 22:54
 */
@Data
public class ArticleBodyVo implements Serializable {

    private static final long serialVersionUID = -8658002045451462436L;

    private String content;

}
