package com.pb.backend.dto;

import com.pb.backend.entities.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Instant;

@Schema(description = "DTO para Pedido", requiredProperties = {"status", "customer", "product", "quantity", "price"})
public class OrderDTO {

    @Schema(description = "ID único do pedido", example = "60f8a1b2c3d4e5f6a7b8c9d0", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @Schema(description = "Momento em que o pedido foi criado", example = "2025-02-02T10:10:10Z", accessMode = Schema.AccessMode.READ_ONLY)
    private Instant moment;

    @Schema(description = "Status do pedido", example = "PENDING")
    @NotBlank(message = "Status é requerido")
    private String status;

    @Schema(description = "Nome do cliente", example = "Thanos")
    @NotBlank(message = "Nome do cliente é requerido")
    private String customer;

    @Schema(description = "Nome do produto", example = "MacBook")
    @NotBlank(message = "Nome do produto é requerido")
    private String product;

    @Schema(description = "Quantidade do produto", example = "2", minimum = "1")
    @Positive(message = "Quantidade deve ser maior que zero")
    private Integer quantity;

    @Schema(description = "Preço unitário do produto", example = "1500.00", minimum = "0.01")
    @PositiveOrZero(message = "Preço deve ser maior que zero")
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

    @Schema(description = "Valor total do pedido (preço × quantidade)", example = "3000.00", accessMode = Schema.AccessMode.READ_ONLY)
    public Double getTotal() {
        return this.price * this.quantity;
    }
}