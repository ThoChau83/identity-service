package org.example.identityservice.service;

import org.example.identityservice.dto.request.AuthenticationRequest;
import org.example.identityservice.entity.User;
import org.example.identityservice.exception.AppException;
import org.example.identityservice.exception.ErrorCode;
import org.example.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    public boolean authenticate(AuthenticationRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       return  passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
