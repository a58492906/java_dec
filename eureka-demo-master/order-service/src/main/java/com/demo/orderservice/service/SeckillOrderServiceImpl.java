package com.demo.orderservice.service;

import com.demo.orderservice.entity.SeckillOrderDTO;
import com.demo.orderservice.model.SeckillOrder;
import com.demo.orderservice.repository.SeckillOrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 23:44
 */
@Component
public class SeckillOrderServiceImpl implements SeckillOrderService
{
    @Autowired
    private SeckillOrderRepository seckillOrderRepository;

    @Override
    public int save(SeckillOrderDTO seckillOrderDTO) {
        SeckillOrder seckillOrder=new SeckillOrder();
        BeanUtils.copyProperties(seckillOrderDTO,seckillOrder);
        seckillOrderRepository.save(seckillOrder);
        return 1;
    }
}
