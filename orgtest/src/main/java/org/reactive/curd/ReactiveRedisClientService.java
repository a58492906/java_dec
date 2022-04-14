package org.reactive.curd;

import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonArray;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-15 00:59
 */
@ApplicationScoped
public class ReactiveRedisClientService  {

    @Inject
    ReactiveRedisClient reactiveRedisClient;



    Uni<JsonArray> result =
            // Step 1 - 获得key
            reactiveRedisClient.keys("*")
                    .onItem().transformToMulti(keys -> Multi.createFrom().iterable(keys))
                    .onItem().castTo(String.class)
                    // Step 2 - 检索相关对象对于每一个key
                    .onItem().transformToUniAndMerge(key -> reactiveRedisClient.hgetall(key))
                    // Step 3 and 4 - 生成包含所有对象的JsonArray
                    .collect().in(JsonArray::new, JsonArray::add);




}