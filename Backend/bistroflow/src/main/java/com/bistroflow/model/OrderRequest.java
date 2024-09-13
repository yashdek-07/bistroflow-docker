package com.bistroflow.model;

import com.bistroflow.model.Orders;
import com.bistroflow.model.OrderDetails;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Orders order;
    private List<OrderDetails> orderDetails;
}
