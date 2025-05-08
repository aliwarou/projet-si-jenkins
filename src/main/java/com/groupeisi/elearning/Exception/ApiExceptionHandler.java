package com.groupeisi.elearning.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiException> handleEntityNotFoundException(EntityNotFoundException e) {
        ApiException exception = new ApiException(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiException> handleValidationException(MethodArgumentNotValidException e) {
        ApiException exception = new ApiException(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {EntityExistException.class})
    public ResponseEntity<ApiException> handleEntityExistException(EntityExistException e) {
        ApiException exception = new ApiException(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiException> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        ApiException exception = new ApiException("Impossible de supprimer cet entité car il est associé à une session", LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);

    }
}

