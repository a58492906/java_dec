package org.reactive.curd;

import com.alibaba.fastjson.JSONObject;
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




    public Uni<Response> getOne() {

        return Uni.createFrom().item(restClient.get());
    }

    public Uni<Response> getWeather(String id) {
        LOGGER.info("id "+id);
        return Uni.createFrom().item(restClient.getById(id)); // 1
    }


   public Uni<Weather> getByName(String name) {
        LOGGER.info("获取数据 "+name);
        String code= cityService.getCode(name);
        LOGGER.info("下面请求第三方数据接口 "+code);
        return Uni.createFrom().item(restClient.getStream(code))
                .onItem().transformToUni(this::invokeRemoteGreetingService)
                .onFailure().recoverWithItem(new Weather()); // 1
    }

    public Uni<Weather> getByCode(String code) {

        return   Uni.createFrom().item(restClient.getStream(code))
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
