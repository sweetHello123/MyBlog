package com.it.api.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/15 18:46
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = -2435352259042716061L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String nickname;

    private String avatar;

}
