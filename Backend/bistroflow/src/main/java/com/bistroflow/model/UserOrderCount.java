package com.bistroflow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserOrderCount {
    @Id
    private Long userId;
    private String userName;
    private Long ordersSold;


}
