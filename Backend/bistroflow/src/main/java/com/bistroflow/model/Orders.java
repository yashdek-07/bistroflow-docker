package com.bistroflow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_contact")
    private String customerContact;

    @Column(name="total_amount")
    private BigDecimal totalAmount;

    @Column(name = "date")
    private LocalDate date;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetails> orderDetailsList;
}