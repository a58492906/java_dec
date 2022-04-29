package com.demo.eureka.service;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 15:26
 */
public interface SeckillLimitService
{

    Boolean  trySeckill(String key,Long limit);
}
