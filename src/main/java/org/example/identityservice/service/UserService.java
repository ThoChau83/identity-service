package org.example.identityservice.service;


import org.example.identityservice.dto.request.UserCreationRequest;
import org.example.identityservice.dto.request.UserUpdateRequest;
import org.example.identityservice.entity.User;
import org.example.identityservice.exception.AppException;
import org.example.identityservice.exception.ErrorCode;
import org.example.identityservice.mapper.UserMapper;
import org.example.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request){


        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user  = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(request.getPassword())
                .dob(request.getDob())
                .build();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

//        cach dung mapper
//        userMapper.toUser(request);


//        cach dung thu cong
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
//
        return userRepository.save(user);
    }

    //Update 1 user by id
    public User updateUser(String userId,UserUpdateRequest request){
        User user = getUser(userId);
        userMapper.updateUser(user, request);

//        Cach lam cu
//        User user  = getUser(userId);
//
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    //Delete user by id
    public void deleteUser(String userid){
         userRepository.deleteById(userid);
    }

    //Find all user
    public List<User> getUser(){
        return userRepository.findAll();
    }


    //Find 1 user by id
    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
