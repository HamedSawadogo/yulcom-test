package com.yulcom.inoutfolderapp.domain.exceptions.validations;

import com.yulcom.inoutfolderapp.domain.exceptions.validations.errors.ValidationError;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record CustomValidationError(
    int status,
    String message,
    LocalDateTime timestamp,
    List<ValidationError> errors
)
{
}
