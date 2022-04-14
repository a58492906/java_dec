package org.reactive.curd;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.reactive.model.Weather;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-06 16:55
 */

@ApplicationScoped
public class ReactiveGreetingService {


    private static final Logger LOGGER = Logger.getLogger(ReactiveGreetingService.class.getName());


    @Inject
    RestClient restClient;

    @Inject
    CityService cityService;

    @Inject
    RedisClientService redisClientService;
    @Inject
    RestClientOkHttp restClientOkHttp;
    private static String[] cityNames = new String[]{"北京", "上海", "广州", "深圳", "南京"};

    private static int[] cityTimes = new int[]{50, 40, 30, 20, 10};

    public Uni<String> testCache() {
        long miles=System.currentTimeMillis();
        LOGGER.info("start " +miles );
        String res="";
        String name="";
        for(int i=0 ;i< cityNames.length;i++){
            name=cityNames[i];
            int times= cityTimes[i];
            while (times>0){
                try {
                    if (null != redisClientService.get(name)) {
                        res =redisClientService.get(name);
                    }else{
                        res =restClientOkHttp.getCityWeather(name);
                        if (null != redisClientService.get(name)) {
                            redisClientService.set(name,res);
                        }
                    }
                    LOGGER.info("res " + res );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                times--;
            }
        }
        LOGGER.info("end " +(System.currentTimeMillis()-miles ));

        return Uni.createFrom().item(res);
    }

    public Uni<Response> getOne() {

        return Uni.createFrom().item(restClient.get());
    }

    public Uni<Response> getWeather(String id) {
        LOGGER.info("id " + id);
        return Uni.createFrom().item(restClient.getById(id)); // 1
    }


    public Uni<Weather> getByName(String name) {

        LOGGER.info("获取数据 " + name);
        String code = cityService.getCode(name);
        LOGGER.info("下面请求第三方数据接口 " + code);
        return Uni.createFrom().item(restClient.getStream(code))
                .onItem().transformToUni(this::invokeRemoteGreetingService)
                .onFailure().recoverWithItem(new Weather()); // 1
    }

    public Uni<Weather> getByCode(String code) {

        return Uni.createFrom().item(restClient.getStream(code))
                .onItem().transformToUni(this::invokeRemoteGreetingService)
                .onFailure().recoverWithItem(new Weather());
    }

    Uni<Weather> invokeRemoteGreetingService(InputStream inputStream) {
        return Uni.createFrom().item(inputStream)
                .onItem().delayIt().by(Duration.ofSeconds(1))
                .onItem().transform(s -> {
                    try {
                        return JSONObject.parseObject(s, Weather.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new Weather();
                });
    }

}
