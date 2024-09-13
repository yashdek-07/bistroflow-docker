package com.bistroflow.service.datewise;

import com.bistroflow.model.DateWiseTotalSales;
import com.bistroflow.repository.DateWiseSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateWiseTotalSalesServiceImpl implements DateWiseTotalSalesService {

    @Autowired
    private DateWiseSalesRepository repository;

    @Override
    public List<DateWiseTotalSales> getDateWiseSales() {
        return repository.findDateWiseSales();
    }
}
