package com.pb.backend.dto;

import com.pb.backend.entities.Order;

import java.time.Instant;

public class OrderDTO {

    private String id;
    private Instant moment;
    private String status;
    private String customer;
    private String product;
    private Integer quantity;
    private Double price;

    public OrderDTO() {
    }

    public OrderDTO(String id, Instant moment, String status, String customer, String product, Integer quantity, Double price) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.customer = order.getCustomer();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();
        this.price = order.getPrice();
    }

    public String getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomer() {
        return customer;
    }

    public String getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTotal() {
        return this.price * this.quantity;
    }
}