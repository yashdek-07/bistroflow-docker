package com.bistroflow.service.monthwisecatagory;

import com.bistroflow.model.MonthWiseCategorySales;
import com.bistroflow.repository.MonthWiseCategorySalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthWiseCategorySalesServiceImpl implements MonthWiseCategorySalesService {

    @Autowired
    private MonthWiseCategorySalesRepository repository;

    @Override
    public List<MonthWiseCategorySales> getMonthWiseCategorySales() {
        return repository.findMonthWiseCategorySales();
    }
}
