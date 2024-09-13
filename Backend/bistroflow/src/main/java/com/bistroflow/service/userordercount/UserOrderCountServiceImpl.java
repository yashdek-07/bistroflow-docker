package com.bistroflow.service.userordercount;

import com.bistroflow.model.UserOrderCount;
import com.bistroflow.repository.UserOrderCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderCountServiceImpl implements  UserOrderCountService {
    @Autowired
    private UserOrderCountRepository repository;

    public List<UserOrderCount> getUserOrderCount() {
        return repository.getUserOrderCount();
    }
}
