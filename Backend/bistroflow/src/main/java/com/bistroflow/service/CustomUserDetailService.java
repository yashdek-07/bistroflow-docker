package com.bistroflow.service;

import com.bistroflow.model.UserPrincipal;
import com.bistroflow.model.Users;
import com.bistroflow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findByEmailId(username);
        return user.map(userFound -> new UserPrincipal(userFound))
                .orElseThrow(() -> new UsernameNotFoundException("User with Email does not exist"));
    }
}
