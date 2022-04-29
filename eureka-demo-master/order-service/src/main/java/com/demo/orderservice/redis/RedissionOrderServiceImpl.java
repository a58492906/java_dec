package com.demo.orderservice.redis;




import com.demo.orderservice.entity.RestResponse;
import com.demo.orderservice.entity.SeckillGoodDTO;
import com.demo.orderservice.entity.SeckillOrderDTO;
import com.demo.orderservice.service.SeckillGoodService;
import com.demo.orderservice.service.SeckillOrderService;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 23:03
 */
@Component
public class RedissionOrderServiceImpl implements RedissionOrderService {

    private static final String LOCK_KEY = "seckill_key";

    @Autowired
    SeckillGoodService seckillGoodService;

    @Autowired
    SeckillOrderService seckillOrderService;

    @Autowired
    private RedissonLocker redissonLocker;

    /**
     * 下单步骤：校验库存，扣库存，创建订单，支付
     *
     */
    @Override
    public Boolean saveOrder(Long sid) {
        try {
            redissonLocker.lock(LOCK_KEY);
            /**
             * 1.查库存
             */
            SeckillGoodDTO goodDTO = seckillGoodService.findById(sid);
            if (goodDTO == null || goodDTO.getStockCount() <= 0 ) {
                throw new RuntimeException("库存不足");
            }

            /**
             * 2.根据查询出来的库存，更新已卖库存数量
             */
            int count = seckillGoodService.updateGoodStock(sid);
            if (count == 0){
                throw new RuntimeException("库存为0");
            }

            /**
             * 3.创建订单
             */
            SeckillOrderDTO order = new SeckillOrderDTO();
            order.setGoodId(goodDTO.getId());
            order.setMoney(BigDecimal.valueOf(100.1));
            order.setCreateTime(new Date());
            order.setPayTime(new Date());
            order.setUserId(10L);
            order.setOrderStatus(0);
            int id = seckillOrderService.save(order);
            if (id > 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redissonLocker.unlock(LOCK_KEY); // 释放锁
        }

        return false;
    }
}