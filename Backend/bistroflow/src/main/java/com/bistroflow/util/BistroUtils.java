package com.bistroflow.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BistroUtils {
    public static ResponseEntity<Map<String, Object>> customResponse(String responseMessage, Object data, HttpStatus httpStatus, String statusMessage){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("data", data);
        responseMap.put("message", responseMessage);
        responseMap.put("status", statusMessage);
        return ResponseEntity.status(httpStatus).body(responseMap);
    }
}
