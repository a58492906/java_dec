package com.demo.orderservice.entity;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-27 23:57
 */

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

    public static final int SUCCESS = 0;

    // 秒杀失败
    public static final int SECKILL_ERROR = 1002;

    private int status;

    private String msg;


    private T data;

    public static RestResponse businessError(  String msg) {
        RestResponse<?> response = new RestResponse<>(SECKILL_ERROR, msg, "");
        return response;
    }
    public static <T> RestResponse success(T data) {
        return new RestResponse<>(SUCCESS, "", data);
    }

    public RestResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
