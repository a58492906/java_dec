package com.demo.eureka.kafka;

import org.jboss.logging.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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

}