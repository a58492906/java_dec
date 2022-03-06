package org.reactive.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.reactive.model.CityInfo;
import org.reactive.model.Data;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-04 16:59
 */
@Getter
@Setter
@ToString
@Schema( description = "天气实体")
public class Weather  {

    private String message;
    @Schema( description = "查询时间")
    private String time;
    private Integer status;
    @Schema( description = "日期")
    private String date;
    @Schema( description = "城市信息")
    private CityInfo cityInfo;
    @Schema( description = "天气预报信息")
    private Data data;


}
