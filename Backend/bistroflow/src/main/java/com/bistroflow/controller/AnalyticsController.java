package com.bistroflow.controller;

import com.bistroflow.model.DateWiseTotalSales;
import com.bistroflow.model.MonthWiseCategorySales;
import com.bistroflow.model.UserOrderCount;
import com.bistroflow.service.datewise.DateWiseTotalSalesService;
import com.bistroflow.service.monthwisecatagory.MonthWiseCategorySalesService;
import com.bistroflow.service.userordercount.UserOrderCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bistroflow.config.JWTFilter;

import java.util.List;

@RestController
@RequestMapping("api/v1/analytics")
public class AnalyticsController {

    @Autowired
    private MonthWiseCategorySalesService monthWiseCategorySalesService;

    @Autowired
    private DateWiseTotalSalesService dateWiseTotalSalesService;

    @Autowired
    private UserOrderCountService userOrderCountService;


    @Autowired
    JWTFilter jwtFilter;

    @GetMapping("/monthwise-category-sold")
    public ResponseEntity<List<MonthWiseCategorySales>> getMonthWiseCategorySales() {

        if(jwtFilter.isAdmin()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        List<MonthWiseCategorySales> res=  monthWiseCategorySalesService.getMonthWiseCategorySales();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/datewise-total-sales")
    public ResponseEntity<List<DateWiseTotalSales>> getDateWiseSales() {
        if(jwtFilter.isAdmin()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<DateWiseTotalSales> res = dateWiseTotalSalesService.getDateWiseSales();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/user-order-count")
    public ResponseEntity<List<UserOrderCount>> getUserOrderCount() {
        if(jwtFilter.isAdmin()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<UserOrderCount> res=userOrderCountService.getUserOrderCount();
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
