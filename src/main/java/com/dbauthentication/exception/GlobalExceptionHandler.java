package com.dbauthentication.exception;

import com.dbauthentication.Dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(UserAlreadyExistsException.class)
   public ResponseEntity<ApiResponse> handleUserExstits(UserAlreadyExistsException exception)
   {
           ApiResponse response = new ApiResponse("FAILED", exception.getMessage());

           return  ResponseEntity
                   .status(HttpStatus.CONFLICT)
                   .body(response);
   }

   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<ApiResponse> handleUserExstits(UserNotFoundException exception)
   {
      ApiResponse response = new ApiResponse("NOT FOUND", exception.getMessage());

      return  ResponseEntity
              .status(HttpStatus.NOT_FOUND)
              .body(response);

   }


}
