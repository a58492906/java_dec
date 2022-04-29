package com.demo.orderservice.service;

import com.demo.orderservice.entity.SeckillGoodDTO;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 23:33
 */

public interface SeckillGoodService {

    SeckillGoodDTO findById(Long id);

    int  updateGoodStock(Long id);
}
