package com.it.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: china wu
 * @Description: 通用返回结果
 * @Date: 2022/5/13 21:33
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {

    private boolean success;

    /**
     * 响应码
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }

    public static Result fail(int code, String msg) {
        return new Result(false, code, msg, null);
    }

}
