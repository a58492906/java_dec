package com.demo.eureka.service;

import com.demo.eureka.SeckillLimitFilter;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-29 15:26
 */
@Component
public class SeckillLimitServiceImpl implements SeckillLimitService {
    private static final Logger LOGGER = Logger.getLogger(SeckillLimitServiceImpl.class.getName());

    private final String LOCK_SCRIPT_PATH = "test.lua";

    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public Boolean trySeckill(String key,Long limit) {
        LOGGER.info("*******key: "+key);
        //缓存查找
        Object res=   redisTemplate.opsForValue().get(key);
        if(null!=res){
            Long number= Long.valueOf(res.toString());
            if(number>=limit){
                return false;
            }
        }else {
            redisTemplate.opsForValue().set(key,0);
        }
        Long number=   redisTemplate.opsForValue().increment(key, 1L);
        LOGGER.info("number "+number);
        if(null!=number &&number<=limit){
            return true;
        }
        return false;
    }



}
