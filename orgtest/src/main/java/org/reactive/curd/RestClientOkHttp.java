package org.reactive.curd;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-15 00:55
 */
@ApplicationScoped
public class RestClientOkHttp {

    private static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());



    public String getCityWeather(String city) throws Exception {
        String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String ret =  response.body().string();
        return ret;
    }


}