package org.reactive.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 最近天气数据
 * @author chaz
 * @date 2020/04/10
 */
@Getter
@Setter
@ToString
public class Yesterday {

    private String date;
    private String high;
    private String low;
    private String ymd;
    private String week;
    private String sunrise;
    private String sunset;
    private Integer aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;


}
