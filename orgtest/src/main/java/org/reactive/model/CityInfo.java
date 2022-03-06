package org.reactive.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-04 16:59
 */
@Getter
@Setter
@ToString
public class CityInfo {

    private String city;
    private Integer citykey;
    private String parent;
    private String updateTime;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCitykey() {
        return citykey;
    }

    public void setCitykey(Integer citykey) {
        this.citykey = citykey;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
