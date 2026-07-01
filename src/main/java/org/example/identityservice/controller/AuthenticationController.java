package org.example.identityservice.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.identityservice.dto.request.AuthenticationRequest;
import org.example.identityservice.dto.request.IntrospectRequest;
import org.example.identityservice.dto.response.ApiResponse;
import org.example.identityservice.dto.response.AuthenticationResponse;
import org.example.identityservice.dto.response.IntrospectResponse;
import org.example.identityservice.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
      var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

//    Cach thu cong
//    // Xử lý logic đăng nhập
//    boolean result = authenticationService.authenticate(request);
//    // Bước 1: Tạo cái hộp quà nhỏ (bằng hàm tạo rỗng)
//    AuthenticationResponse authResponse = new AuthenticationResponse();
//    // Nhét dữ liệu true/false vào hộp nhỏ
//        authResponse.setAuthenticated(result);
//    // Bước 2: Tạo cái hộp quà TO (bằng hàm tạo rỗng)
//    ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
//    // Nhét cái hộp nhỏ vào trong khe "result" của hộp TO
//        apiResponse.setResult(authResponse);
//    // Bước 3: Gửi hộp TO về cho người dùng
//        return apiResponse;
}
