package org.example.identityservice.controller;

import jakarta.validation.Valid;
import org.example.identityservice.dto.response.ApiResponse;
import org.example.identityservice.dto.request.UserCreationRequest;
import org.example.identityservice.dto.request.UserUpdateRequest;
import org.example.identityservice.entity.User;
import org.example.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request)
    {
        ApiResponse<User> apiResponse =  new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<User>> getUser(){
        ApiResponse<List<User>> apiResponse =  new ApiResponse<>();
        apiResponse.setResult(userService.getUser());
        return apiResponse;
     }

     @GetMapping("/{userid}")
     ApiResponse<User> getUser(@PathVariable("userid") String userId){
         ApiResponse<User> apiResponse =  new ApiResponse<>();
         apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
     }

     @PutMapping("/{userId}")
     ApiResponse<User> updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UserUpdateRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, request));
        return apiResponse;
     }

     @DeleteMapping("/{userId}")
     ApiResponse<String> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
         ApiResponse<String> apiResponse = new ApiResponse<>();
         apiResponse.setResult("User has been deleted");
        return apiResponse;
     }


}
