package com.demo.orderservice.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.eureka.SeckillOrderRequestDTO;
import com.demo.orderservice.entity.SeckillOrderDTO;
import com.demo.orderservice.kafka.KafkaService;
import com.demo.orderservice.redis.RedissionOrderService;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 00:28
 */
@Component
public class SeckillServiceImpl implements SeckillService {
    private static final Logger LOGGER = Logger.getLogger(SeckillServiceImpl.class.getName());

    @Resource
    private RedissionOrderService redissionOrderService;
    @Resource
    private KafkaService kafkaService;
    @Override
    public SeckillOrderDTO executeSeckill(SeckillOrderRequestDTO dto) {
        LOGGER.info("executeSeckill: "+dto);
       // redissionOrderService.saveOrder(dto.getGoodId());
        return null;
    }

    @Override
    public boolean checkStartSeckill(int stallActivityId) {
        return false;
    }
}
