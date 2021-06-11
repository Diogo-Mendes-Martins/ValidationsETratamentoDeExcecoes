package com.residencia.dell.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "orderlines")
public class Orderlines implements Serializable{

    @Id
    @SequenceGenerator(name = "sequencia", sequenceName = "orderlines_orderlinesid_seq", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequencia")
    @Column(name = "orderlineid", unique=true, updatable = false)
    private Integer orderlineId;

    @ManyToOne //Relacionamento com Orders
    @JoinColumn(name = "orderid", nullable = true)
    private Orders orderId;

    @ManyToOne //Relacionamento com Products
    @JoinColumn(name = "prod_id")
    private Products prodId;

    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "orderdate")
    private Timestamp orderDate;

    //Getters & Setters
    public Integer getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(Integer orderlineId) {
        this.orderlineId = orderlineId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Products getProdId() {
        return prodId;
    }

    public void setProdId(Products prodId) {
        this.prodId = prodId;
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
