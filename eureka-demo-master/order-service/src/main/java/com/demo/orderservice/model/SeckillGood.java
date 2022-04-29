package com.demo.orderservice.model;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-27 23:49
 */

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2021-11-01 16:51
 */
@Data
@Entity
@Table(name = "seckill_good")
public class SeckillGood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //商品标题
    private String title;

    //商品照片
    private String image;

    //商品原价格
    private BigDecimal price;

    //商品秒杀价格
    private BigDecimal costPrice;

    //创建时间

    private Date createTime;

    //秒杀开始时间
    private Date startTime;

    //秒杀结束时间

    private Date endTime;


    //剩余库存数量
    private long stockCount;

}