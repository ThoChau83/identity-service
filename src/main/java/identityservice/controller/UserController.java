package identityservice.controller;

import jakarta.validation.Valid;
import identityservice.dto.response.ApiResponse;
import identityservice.dto.request.UserCreationRequest;
import identityservice.dto.request.UserUpdateRequest;
import identityservice.entity.User;
import identityservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));


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
