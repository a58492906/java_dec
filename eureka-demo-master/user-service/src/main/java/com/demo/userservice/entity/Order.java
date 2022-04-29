package com.demo.userservice.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2021/3/15
 * @Time 18:28
 * @Author KongDeChang
 * @Desc Order
 * @Email zvxf@outlook.com
 */

public class Order implements Serializable {

    private static final long serialVersionUID = -95562768475254267L;

    private Integer id;

    private Integer userId;

    private Double amount;

    private Date createTime;

    private String note;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", amount=").append(amount);
        sb.append(", createTime=").append(createTime);
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
