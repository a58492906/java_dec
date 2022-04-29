package com.demo.orderservice.kafka;

import com.alibaba.fastjson.JSON;
import com.demo.eureka.SeckillOrderRequestDTO;
import com.demo.orderservice.redis.RedissionOrderService;
import com.demo.orderservice.service.SeckillServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 23:04
 */
@Component
public class KafkaService {
    private static final Logger LOGGER = Logger.getLogger(KafkaService.class.getName());
    @Resource
    private KafkaUtils kafkaUtils;
    @Resource
    private RedissionOrderService redissionOrderService;
    /**
     * 生产者往topic中发送消息demo
     *
     * @param topic
     * @param message
     * @return
     */
    public  boolean sendMessage(String topic, String message) {
        kafkaUtils.sendMessage(topic, message);
        return true;
    }

    /**
     * 消费者示例demo
     * <p>
     * 基于注解监听多个topic，消费topic中消息
     * （注意：如果监听的topic不存在则会自动创建）
     */
    @KafkaListener(topics = {"topic1"})
    public void consume(String message) {

        LOGGER.info("receive msg: " + message );
        SeckillOrderRequestDTO dto= JSON.parseObject(message,SeckillOrderRequestDTO.class);
        redissionOrderService.saveOrder(dto.getGoodId());

    }
}