package com.demo.orderservice.entity;

import lombok.Data;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-27 23:21
 */
@Data
public class BaseResponse{

    //请求是否成功
    private Boolean isSuccess = true;
    //请求响应码，成功时为0
    private int responseCode = 0;
    //请求响应码对应描述
    private String responseMsg = "请求成功";

}
