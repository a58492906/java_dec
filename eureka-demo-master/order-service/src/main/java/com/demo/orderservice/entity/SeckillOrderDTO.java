package com.demo.orderservice.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 00:00
 */
@Data
public class SeckillOrderDTO {
    //订单ID
    private Long id;


    //支付金额
    private BigDecimal money;


    //秒杀用户的用户ID
    private Long userId;

    //创建时间
    private Date createTime;


    //支付时间

    private Date payTime;


    //秒杀商品，和订单是一对多的关系
    private Long goodId;

    //订单状态， -1:无效 0:成功 1:已付款
    private Integer orderStatus ;

}
