package com.demo.orderservice.repository;

import com.demo.orderservice.model.SeckillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 22:24
 */
@Repository
public interface SeckillOrderRepository extends JpaRepository<SeckillOrder, Long>,
        JpaSpecificationExecutor<SeckillOrder> {


}