package com.demo.eureka;

import lombok.Data;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 00:02
 */
@Data
public class SeckillOrderRequestDTO {

    private long goodId;
    private Double money;
    private long userId;
}
