package com.whyzaa.vspringblog.util;

import lombok.Data;

@Data
public class ResponseData<T> {

    /**
     * 统一返回码
     */
    public Integer code;

    /**
     * 统一消息
     */
    public String msg;

    /**
     * 结果T
     */
    public T data;

    public ResponseData() {
    }

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
