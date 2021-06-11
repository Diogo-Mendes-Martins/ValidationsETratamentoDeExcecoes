package com.residencia.dell.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

public class OrderlinesDTO implements Serializable {

    private Integer orderlineId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Timestamp orderDate;

    //Getters & Setters
    public Integer getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(Integer orderlineId) {
        this.orderlineId = orderlineId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
