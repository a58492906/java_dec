package com.demo.orderservice.service;

import com.demo.orderservice.entity.SeckillGoodDTO;
import com.demo.orderservice.model.SeckillGood;
import com.demo.orderservice.repository.SeckillGoodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 23:34
 */
@Component
public class SeckillGoodServiceImpl implements SeckillGoodService{

    @Autowired
    private  SeckillGoodRepository seckillGoodRepository;

    @Override
    public SeckillGoodDTO findById(Long id) {
        SeckillGoodDTO seckillGoodDTO=new SeckillGoodDTO();

        Optional<SeckillGood> optionalSeckillGood= seckillGoodRepository.findById(id);
        if(!optionalSeckillGood.isPresent()){

            throw  new RuntimeException("商品不存在");
        }
        BeanUtils.copyProperties(optionalSeckillGood.get(),seckillGoodDTO);
        return seckillGoodDTO;
    }

    @Override
    public int updateGoodStock(Long id) {
        Optional<SeckillGood> optionalSeckillGood= seckillGoodRepository.findById(id);

        if(!optionalSeckillGood.isPresent()){
            throw  new RuntimeException("商品不存在");
        }
        SeckillGood seckillGood=optionalSeckillGood.get();

        if(seckillGood.getStockCount()<=0){
            return 0;
        }
        seckillGood.setStockCount(seckillGood.getStockCount()-1);
        seckillGoodRepository.saveAndFlush(seckillGood);
        return 1;
    }
}
