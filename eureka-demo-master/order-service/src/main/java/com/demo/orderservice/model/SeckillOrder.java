package com.demo.orderservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-25 23:12
 */

@Data
@Entity
@Table(name = "seckill_order")
public class SeckillOrder {
    //订单ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
