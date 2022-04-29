package com.demo.orderservice.service;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-27 22:50
 */

import com.demo.eureka.SeckillOrderRequestDTO;
import com.demo.orderservice.entity.SeckillOrderDTO;


/**
 * 秒杀接口
 */

public interface SeckillService
{

        /**
         * 秒杀处理主要逻辑
         */
         SeckillOrderDTO executeSeckill(SeckillOrderRequestDTO dto);

        /**
         * 判断秒杀活动是否已经开始
         */
         boolean checkStartSeckill(int stallActivityId);


}
