package com.bistroflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderdetails")
@IdClass(OrderDetailsId.class)  // To handle composite key
public class OrderDetails implements Serializable {

    @Id
    @Column(name = "order_id", insertable = false, updatable = false)
    private int orderId;

    @Id
    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "individual_amount")
    private BigDecimal individualAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonBackReference
    private Orders orders;
}
