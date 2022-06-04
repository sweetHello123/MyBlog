package com.it.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/13 22:16
 */
@Data
public class TagVo implements Serializable {

    private static final long serialVersionUID = 4328571903665324678L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String avatar;

    private String tagName;

}
