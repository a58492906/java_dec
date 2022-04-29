package com.demo.eureka.controller;

import com.demo.eureka.RestResponse;
import com.demo.eureka.SeckillClientService;
import com.demo.eureka.SeckillOrderRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-25 23:12
 */
@RestController
@RequestMapping("/api/seckill/")
public class Service2Controller {
    @Resource
    private SeckillClientService seckillClientService;

    @RequestMapping("service1")
    public String findOrderByUserId() {
        return "service1";
    }

    /**
     * 执行秒杀的操作
     *
     */
    @RequestMapping(value = "/do", method = {RequestMethod.POST})
    RestResponse executeSeckill(@RequestBody SeckillOrderRequestDTO dto
    ) {
        String res = seckillClientService.executeSeckill(dto);
        return RestResponse.success(res);

    }
}
