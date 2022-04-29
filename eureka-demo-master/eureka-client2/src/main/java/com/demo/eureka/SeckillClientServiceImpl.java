package com.demo.eureka;

import com.alibaba.fastjson.JSONObject;
import com.demo.eureka.kafka.KafkaService;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 23:36
 */
@Component
public class SeckillClientServiceImpl implements SeckillClientService {
    private static final Logger LOGGER = Logger.getLogger(SeckillClientServiceImpl.class.getName());


    @Resource
    private KafkaService kafkaService;

    @Override
    public String executeSeckill(SeckillOrderRequestDTO dto) {
        LOGGER.info("executeSeckill: " + dto);
        String jsonString = JSONObject.toJSONString(dto);
        kafkaService.sendMessage("topic1", jsonString);
        // redissionOrderService.saveOrder(dto.getGoodId());
        return null;
    }


}