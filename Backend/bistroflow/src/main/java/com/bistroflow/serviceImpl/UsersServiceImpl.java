package com.bistroflow.serviceImpl;

import com.bistroflow.model.Users;
import com.bistroflow.repository.UserRepo;
import com.bistroflow.service.UsersService;
import com.bistroflow.util.BistroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.bistroflow.constant.BistroConstant.*;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    UserRepo userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public ResponseEntity<Map<String, Object>> signUp(Users user) {
        Optional<Users> doesCustomerExist = userRepo.findByEmailId(user.getEmailId());

        if(doesCustomerExist.isEmpty()){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
            return BistroUtils.customResponse("User Saved!", user, HttpStatus.OK, SUCCESS);
        }

        return BistroUtils.customResponse("User with the emailId already exists!", null, HttpStatus.BAD_REQUEST, FAILED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> verifyUser(Users user){
        System.out.println(user.getEmailId() + " " + user.getPassword());
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
        if(authentication.isAuthenticated()){
            Users currentUser = userRepo.findByEmailId(user.getEmailId()).orElse(null);
            String jwtToken = jwtService.generateToken(user.getEmailId(), currentUser.getRole());
            return BistroUtils.customResponse("User verified", jwtToken, HttpStatus.OK, SUCCESS);
        }
        else return BistroUtils.customResponse("Failed to verify!", null, HttpStatus.INTERNAL_SERVER_ERROR, FAILED);
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(Map<String, String> requestMap) {
        return null;
    }
}
