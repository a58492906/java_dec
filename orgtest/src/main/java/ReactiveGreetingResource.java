
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.reactive.curd.ReactiveGreetingService;
import org.reactive.model.Weather;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-04 15:14
 */


@Path("weather")
@Tag(name = "WeatherResource",description = "获取天气预报")
public class ReactiveGreetingResource {
    @Inject
    ReactiveGreetingService service;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "获取天气预报", description = "这是一个获取默认城市天气预报的接口")
    public Uni<Response> getOne() {
        return service.getOne();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getByName/{name}")
    @Operation(summary = "城市名称天气预报", description = "这是一个根据城市名称获取天气预报的接口")
    public Uni<Weather> getByName(@PathParam("name") String name) {
        return service.getByName(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getByCode/{code}")
    @Operation(summary = "城市代码天气预报", description = "这是一个根据城市code获取天气预报的接口")
    public Uni<Weather> getByCode(@PathParam("code") String code) {
        return service.getByCode(code);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/testByTimes")
    @Operation(summary = "城市代码天气预报", description = "这是一个根据城市code获取天气预报的接口")
    public Uni<String> testCache() {
        return service.testCache();
    }

}

