package com.bistroflow.controller;

import com.bistroflow.config.JWTFilter;
import com.bistroflow.model.Users;
import com.bistroflow.service.UsersService;
import com.bistroflow.serviceImpl.JWTService;
import com.bistroflow.util.BistroUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import static com.bistroflow.constant.BistroConstant.*;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    JWTFilter jwtFilter;

    @PostMapping(path ="/signup")
    public ResponseEntity<Map<String,Object>> signUp(@Valid @RequestBody(required = true)Users user){
        try{
            System.out.println("inside controller signup");
            System.out.println(user.getPassword());
            return usersService.signUp(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return BistroUtils.customResponse(SOMETHING_WENT_WRONG,null, HttpStatus.INTERNAL_SERVER_ERROR,FAILED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody(required = true) Users user){
        try{
            //return usersService.login(requestMap);
            System.out.println(user.getEmailId() + " " + user.getPassword());
            return usersService.verifyUser(user);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return BistroUtils.customResponse(SOMETHING_WENT_WRONG,null, HttpStatus.INTERNAL_SERVER_ERROR, FAILED);
    }

    @GetMapping(path = "/test")
    public ResponseEntity<Map<String,Object>> test(){

        if(jwtFilter.isAdmin())
        return BistroUtils.customResponse("Admin", null, HttpStatus.OK,SUCCESS);
        else if (jwtFilter.isUser()) {
            return BistroUtils.customResponse("User", null, HttpStatus.OK,SUCCESS);
        }
        return BistroUtils.customResponse("Done", null, HttpStatus.OK,SUCCESS);
    }
}
