package com.yulcom.inoutfolderapp.domain.exceptions.validations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yulcom.inoutfolderapp.application.dtos.out.HttpErrorResponse;
import io.micrometer.common.lang.NonNullApi;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.util.MimeType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@NonNullApi
@RestControllerAdvice
public class ErrorsHandler extends ResponseEntityExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(ErrorsHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        final HttpMessageNotReadableException exception,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request
    )
    {
        String errorMessage = "La requête est male formattée";
        log.error("***** {} *****", errorMessage, exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage)
            .exception(exception.getLocalizedMessage())
            .dateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(status).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException exception,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request
    )
    {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ValidationErrorData> validations = new ArrayList<>(fieldErrors.size());
        fieldErrors.forEach(fieldError -> {
            Optional<ValidationErrorData> optValidationError = validations.stream()
                .filter(errorData ->
                    Objects.equals(errorData.field(), fieldError.getField())
                ).findFirst();

            if (optValidationError.isPresent())
            {
                validations.remove(optValidationError.get());
                List<String> messages = optValidationError.get().messages();
                String fieldErrorMessage = fieldError.getDefaultMessage();
                messages.add(fieldErrorMessage);
                var validationErrorData = ValidationErrorData.builder()
                    .object(fieldError.getObjectName())
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue())
                    .messages(messages)
                    .build();
                validations.add(validationErrorData);
            } else
            {
                validations.add(new ValidationErrorData(fieldError));
            }
        });
        String errorMessage = "La requête contient des données invalides";
        log.error("***** {} *****", errorMessage, exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage)
            .validationErrors(validations)
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        final NoHandlerFoundException exception,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request
    )
    {
        StringBuilder errorMessage = new StringBuilder("Aucune ressource trouvée pour");
        errorMessage.append(" ")
            .append(exception.getHttpMethod())
            .append(" ")
            .append(exception.getRequestURL());
        log.error("***** {} *****", errorMessage, exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage.toString())
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        final HttpRequestMethodNotSupportedException exception,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request)
    {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Cette méthode HTTP ")
            .append(String.format("[%s] ", exception.getMethod()))
            .append("n'est pas prise en compte. ");
        Set<HttpMethod> supportedHttpMethods = exception.getSupportedHttpMethods();
        if (supportedHttpMethods != null)
        {
            errorMessage
                .append("Les méthodes valides sont : ")
                .append(supportedHttpMethods.stream().map(HttpMethod::toString).toList());
        }
        log.error("***** {} *****", errorMessage, exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage.toString())
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        final HttpMediaTypeNotSupportedException exception,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request)
    {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Ce type de format ")
            .append(String.format("[%s] ", exception.getContentType()))
            .append("n'est pas autorisé. ")
            .append("Les formats autorisés sont : ");
        errorMessage.append(exception.getSupportedMediaTypes()
            .stream()
            .map(MimeType::getType).toList());
        log.error("***** {} *****", errorMessage, exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage.toString())
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
        final MissingPathVariableException exception,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request)
    {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("La variable de chemin ")
            .append(String.format("[%s]", exception.getVariableName()))
            .append("est manquante.");
        log.error("***** {} *****", errorMessage, exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage.toString())
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
        TypeMismatchException exception,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request)
    {
        String errorMessage = "Mauvaise correspondance de type pour "
            + String.format("'%s'", exception.getPropertyName())
            + ". Impossible de convertir "
            + String.format("[%s] en ", exception.getValue())
            + String.format("[%s]",
            Objects.requireNonNull(exception.getRequiredType()).getSimpleName());
        log.error("Mauvaise correspondance de type.", exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage)
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
        ConversionNotSupportedException exception,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request)
    {
        String errorMessage =
            String.format("La conversion n'est pas possible pour [%s]. ",
                exception.getPropertyName())
                + ". Impossible de convertir "
                + String.format("[%s] en ", exception.getValue())
                + String.format("[%s]",
                Objects.requireNonNull(exception.getRequiredType()).getSimpleName());
        log.error("Conversion impossible", exception);
        var response = HttpErrorResponse.builder()
            .status(status.value())
            .errorMessage(errorMessage)
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationError(ConstraintViolationException ex)
    {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<ValidationErrorData> validations = new ArrayList<>(constraintViolations.size());
        constraintViolations.forEach(constraintViolation -> {
            Optional<ValidationErrorData> optValidationError = validations.stream()
                .filter(errorData -> Objects.equals(errorData.field(), constraintViolation.getPropertyPath().toString())).findFirst();
            if (optValidationError.isPresent())
            {
                validations.remove(optValidationError.get());
                List<String> messages = optValidationError.get().messages();
                messages.add(constraintViolation.getMessage());
                var validationErrorData = ValidationErrorData.builder()
                    .field(constraintViolation.getPropertyPath().toString())
                    .rejectedValue(constraintViolation.getInvalidValue())
                    .messages(messages)
                    .build();

                validations.add(validationErrorData);
            } else
            {
                validations.add(
                    ValidationErrorData.builder()
                        .field(constraintViolation.getPropertyPath().toString())
                        .rejectedValue(constraintViolation.getInvalidValue())
                        .messages(Collections.singletonList(constraintViolation.getMessage()))
                        .build()
                );
            }
        });
        String errorMessage = "Violation de contraintes";
        log.error("***** {} *****", errorMessage, ex);
        var response = HttpErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .errorMessage(errorMessage)
            .dateTime(LocalDateTime.now())
            .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception)
    {
        log.error("***** EntityNotFoundException *****", exception);
        var response = HttpErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .errorMessage(exception.getMessage())
            .dateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(response.status()).body(response);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> handleResourceNotFoundException(
//        ResourceNotFoundException exception)
//    {
//        log.error("***** ResourceNotFoundException *****", exception);
//        var response = HttpErrorResponse.builder()
//            .status(HttpStatus.NOT_FOUND.value())
//            .errorMessage(exception.getMessage())
//            .dateTime(LocalDateTime.now())
//            .build();
//        return ResponseEntity.status(response.status()).body(response);
//    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<Object> handleMissingAttributeException(BadRequestException exception)
//    {
//        log.error("***** BadRequestException *****", exception);
//        var response = HttpErrorResponse.builder()
//            .status(HttpStatus.BAD_REQUEST.value())
//            .errorMessage(exception.getMessage())
//            .dateTime(LocalDateTime.now())
//            .build();
//        return ResponseEntity.status(response.status()).body(response);
//    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<Object> handleJpaSystemException(JpaSystemException exception)
    {
        log.error("***** JpaSystemException *****", exception);
        var response = HttpErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorMessage(exception.getMessage())
            .dateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException exception)
    {
        log.error("***** JsonProcessingException *****", exception);
        var response = HttpErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorMessage(exception.getMessage())
            .dateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


    private HttpErrorResponse buildResponse(HttpStatus status, String code, String message, List<ValidationErrorData> validationErrors, List<Error> errors) {
        return HttpErrorResponse.builder()
                .status(status.value())
                .code(code)
                .errors(errors)
                .errorMessage(message)
                .validationErrors(validationErrors)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
