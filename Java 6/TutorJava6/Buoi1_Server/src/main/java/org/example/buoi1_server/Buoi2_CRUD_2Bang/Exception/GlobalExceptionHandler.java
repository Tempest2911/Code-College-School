package org.example.buoi1_server.Buoi2_CRUD_2Bang.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidate(MethodArgumentNotValidException exception) {
        Map<String, String> errormap = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(e -> errormap.put(e.getField(), e.getDefaultMessage()));

        return new ResponseEntity<>(errormap, HttpStatus.BAD_REQUEST);
    }

    // custom loi 400 nhung do chung tu tao
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handlerApiException(ApiException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("status","FAIL");
        errorMap.put("code", exception.getCode());
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    // handel cac loi con  => 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerAll(Exception exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("status","FAIL");
        errorMap.put("code", "INTERNAL_SERVER_ERROR");
        errorMap.put("message", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
