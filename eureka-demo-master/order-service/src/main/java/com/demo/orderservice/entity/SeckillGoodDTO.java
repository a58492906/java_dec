package com.demo.orderservice.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-27 23:56
 */
@Data
public class SeckillGoodDTO {

    private Long id;
    //商品标题
    private String title;

    //商品照片
    private String image;

    //商品原价格
    private Double price;

    //商品秒杀价格
    private Double costPrice;

    //创建时间

    private Date createTime;

    //秒杀开始时间
    private Date startTime;

    //秒杀结束时间

    private Date endTime;

    //剩余库存数量
    private long stockCount;

}