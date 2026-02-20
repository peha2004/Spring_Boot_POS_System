package org.example._1_back_end.exception;

import org.example._1_back_end.utill.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> HandleGenericException(Exception e){
       return new ResponseEntity<>(new APIResponse<>(
               HttpStatus.INTERNAL_SERVER_ERROR.value(),
               "internal server error",
               null
       ),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse<String>>handleNullPointerException(NullPointerException e){
        return new ResponseEntity<>(new APIResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "null values are not assigned",
                e.getMessage()
        ),HttpStatus.BAD_REQUEST);



    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Object>> hadleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            errors.put(error.getObjectName(), e.getFieldError().getDefaultMessage());

                });
        return new ResponseEntity<>(new APIResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "validation faild",
                errors
        ),HttpStatus.BAD_REQUEST);
    }
}
