package com.bistroflow.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@IdClass(MonthWiseCategorySalesId.class)
public class MonthWiseCategorySales {

    @Id
    private String month;
    @Id
    private int categoryId;
    private Double totalSales;
    private String category_name;

}
