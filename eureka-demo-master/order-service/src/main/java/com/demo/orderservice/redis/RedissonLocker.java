package com.demo.orderservice.redis;



import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 23:02
 */
public interface RedissonLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, long timeout);

    RLock lock(String lockKey, TimeUnit unit, long timeout);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}