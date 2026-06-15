package com.portfolio.contactBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.portfolio.contactBackend.dto.ApiResponse;
import com.portfolio.contactBackend.dto.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.put(error.getField(), error.getDefaultMessage());
//        });
//
//        return errors;
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
//    public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getConstraintViolations().forEach(error -> {
//            errors.put(error.getPropertyPath().toString(), error.getMessage());
//        });
//
//        return errors;
//    }
	
	//Invalid Login Handler	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<?> handleValidation(
//	        MethodArgumentNotValidException ex) {
//
//	    Map<String, String> errors = new HashMap<>();
//
//	    ex.getBindingResult()
//	            .getFieldErrors()
//	            .forEach(error ->
//	                    errors.put(
//	                            error.getField(),
//	                            error.getDefaultMessage()
//	                    ));
//
//	    return ResponseEntity.badRequest().body(errors);
//	}
    
    //User not found exception    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(
            UserNotFoundException ex) {
    	ErrorResponse response = new ErrorResponse(
                false,
                ex.getMessage(),
                "USER_404",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    
    //User unauthorized exception    
    @ExceptionHandler( UserUnauthorizedException.class )
    public ResponseEntity<ErrorResponse> handleUnauthorized( UserUnauthorizedException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        false,
                        ex.getMessage(),
                        "AUTH_401",
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGenericException(Exception ex) {

        ErrorResponse error =
                new ErrorResponse(
                        false,
                        "Something went wrong",
                        "INTERNAL_SERVER_ERROR",
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}