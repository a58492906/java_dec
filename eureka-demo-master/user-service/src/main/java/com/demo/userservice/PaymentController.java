package com.demo.userservice;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-26 00:07
 */
@RestController
public class PaymentController {
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class.getName());


    @Resource
    private PaymentService paymentService;


    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        LOGGER.info("*******result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        LOGGER.info("*******result:" + result);
        return result;
    }

    @GetMapping("/Circuit/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        String result = paymentService.circuitBreaker(id);
        LOGGER.info("*******result:" + result);
        return result;
    }
}

