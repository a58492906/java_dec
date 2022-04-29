package com.demo.eureka;


/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 23:35
 */
public interface SeckillClientService
{

    /**
     * 秒杀处理主要逻辑
     */
    String executeSeckill(SeckillOrderRequestDTO dto);

}
