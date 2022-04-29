package com.demo.orderservice.controller;

import com.demo.eureka.SeckillOrderRequestDTO;
import com.demo.orderservice.entity.*;
import com.demo.orderservice.service.SeckillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-25 23:12
 */


@RestController
@RequestMapping("/api/seckill/")
public class SeckillController {
    @Resource
    SeckillService seckillService;


    /**
     * 获取所有的秒杀商品列表
     *
     * @param pageReq 当前页 ，从1 开始,和 页的元素个数
     * @return
     */
    @PostMapping("/list")
    RestResponse  findAll(@RequestBody QueryDTO pageReq) {
       // PageView<SeckillGoodDTO> page = seckillService.findAll(pageReq);
       // Result<PageView<SeckillGoodDTO>> r = Result.success(page);
        return null;

    }



    /**
     * 执行秒杀的操作
     *
     */
    @RequestMapping(value = "/do", method = {RequestMethod.POST})
    RestResponse executeSeckill(@RequestBody SeckillOrderRequestDTO dto
    ) {
        SeckillOrderDTO res = seckillService.executeSeckill(dto);
        return RestResponse.success(res);

    }
    /**
     * 增加秒杀的商品
     *
     * @param stockCount 库存
     * @param title      标题
     * @param price      商品原价格
     * @param costPrice  价格
     * @return
     */
    @GetMapping("/good/add")
    RestResponse<SeckillGoodDTO>  executeSeckill(
            @RequestParam(value = "stockCount", required = true) long stockCount,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "price", required = true) BigDecimal price,
            @RequestParam(value = "costPrice", required = true) BigDecimal costPrice) {
        //= seckillService.addSeckillGood(stockCount, title, price, costPrice);
        return null;
    }


}
