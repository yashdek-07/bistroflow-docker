// Product here is the Owning(child) side and Category will be the parent side
package com.bistroflow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;


    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Column(name = "name")
    private String productName;

    @NotBlank(message = "Product description is required")
    @Size(min = 5, max = 500, message = "Product description must be between 5 and 500 characters")
    @Column(name = "description")
    private String productDescription;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than 0")

    @Column(name = "price")
    private double productPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="category_id")
    @JsonBackReference
    Category category;



}
