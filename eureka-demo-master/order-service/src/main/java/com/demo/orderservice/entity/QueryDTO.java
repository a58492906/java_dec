package com.demo.orderservice.entity;

import java.io.Serializable;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-04-28 00:13
 */
public class QueryDTO implements Serializable {


    private static final long serialVersionUID = 8360412442904495933L;

    private int defaultPage = 1;

    private int defaultSize = 10;

    private Integer page;

    private Integer size;

    public int getPage() {
        if(page == null || page <= 0){
            return defaultPage;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getSize() {
        if(size == null || size <=0){
            return defaultSize;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
