package com.yulcom.inoutfolderapp.domain.exceptions.handlers;

import com.yulcom.inoutfolderapp.domain.exceptions.validations.CustomValidationError;
import com.yulcom.inoutfolderapp.domain.exceptions.validations.errors.ValidationError;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomValidationError> handleValidationException(
        MethodArgumentNotValidException ex)
    {
        List<ValidationError> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
        {
            errors.add(new ValidationError(fieldError.getField(), fieldError.getCode()));
        }
        for (ObjectError globalError : ex.getBindingResult().getGlobalErrors())
        {
            errors.add(new ValidationError("global", globalError.getDefaultMessage()));
        }
        CustomValidationError response = new CustomValidationError(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            LocalDateTime.now(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
