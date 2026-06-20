package org.example.identityservice.exception;


import org.example.identityservice.dto.request.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiRespone> handlingRunTimeException(RuntimeException exception){
        ApiRespone  apiRespone = new ApiRespone<>();
        apiRespone.setCode(1001);
        apiRespone.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespone> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiRespone  apiRespone = new ApiRespone<>();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);

        ApiRespone  apiRespone = new ApiRespone<>();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }


}
