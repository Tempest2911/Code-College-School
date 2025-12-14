package org.example.ontap1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandel {

    // 1. Bắt lỗi Validate (@Valid, @NotBlank...) -> Trả về 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Chỉ lấy lỗi đầu tiên của mỗi trường để hiển thị cho gọn
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", "FAILED");
        response.put("code", "VALIDATION_ERROR");
        response.put("errors", errors); // Trả về Map lỗi: "maDonHang": "Trống", "tongTien": "Âm"

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 2. Bắt lỗi Logic (ApiException) -> Trả về 400
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "FAILED");
        response.put("code", ex.getCode());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 3. Bắt các lỗi còn lại (Code sai, DB lỗi...) -> Trả về 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "FAILED");
        response.put("code", "INTERNAL_ERROR");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}