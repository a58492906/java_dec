package com.demo.orderservice.redis;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 00:31
 */
public interface RedissionOrderService {

    Boolean saveOrder(Long sid);
}
