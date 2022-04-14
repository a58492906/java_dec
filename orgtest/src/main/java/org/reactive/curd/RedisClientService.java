package org.reactive.curd;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-13 23:39
 */
@ApplicationScoped
public class RedisClientService  {

    @Inject
    RedisClient redisClient;





    public String get(String key) {
        Response response = redisClient.get(key);
        return response == null ? null : response.toString();

    }


    void set(String key, String value) {
        redisClient.set(Arrays.asList(key, value.toString()));
    }



}