package org.reactive.model;
/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-04 15:22
 */

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Schema( description = "这是一个城市的传输实体")
@Table(name = "city")
public class City {
    @Id
    @Schema(hidden = true)
    private Integer id;

    @Column(name = "code")
    @Schema(title = "城市代码", required = true, example = "1000101")
    private String code;

    @Column(name = "name")
    @Schema(title = "城市名称", required = true, example = "北京")
    private String name;

    public City() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "org.reactive.model.City{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

