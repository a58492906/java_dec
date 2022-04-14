package org.reactive.curd;
/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-04 16:15
 */
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@ApplicationScoped
public class RestClient {

    private static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());

    private static final String weather_url = "http://t.weather.sojson.com/api/weather/city/";
    private static final String weather_url2 = "http://wthrcdn.etouch.cn/weather_mini?city=";

    private final Client httpClient;

    public RestClient() {
        this.httpClient = ResteasyClientBuilder.newBuilder().build();
    }

    public Response get() {


        return httpClient.target(weather_url+"101110203").request().get();
    }

    public InputStream getStream(String id) {
        LOGGER.info("getStream " +id );
        return   httpClient.target(weather_url+id).request(
                ).get(InputStream.class);
    }
    public InputStream getStreamByName(String name) {
        LOGGER.info("getStreamByName " +name );
        return   httpClient.target(weather_url2+name).request(
        ).get(InputStream.class);
    }


    public Response getById(String id) {


        return httpClient.target(weather_url+id).request().get();
    }
}