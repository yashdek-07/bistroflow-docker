package com.bistroflow.service;

import com.bistroflow.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UsersService {
    ResponseEntity<Map<String, Object>> signUp(Users user);
    ResponseEntity<Map<String, Object>> verifyUser(Users user);
    ResponseEntity<Map<String, Object>> login(Map<String, String> requestMap);
}
