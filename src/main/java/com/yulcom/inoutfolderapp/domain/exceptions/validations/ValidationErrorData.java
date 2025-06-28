package com.yulcom.inoutfolderapp.domain.exceptions.validations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record ValidationErrorData(
    String object,
    String field,
    Object rejectedValue,
    List<String> messages
)
{
    public ValidationErrorData(FieldError error)
    {
        this(
            error.getObjectName(),
            error.getField(),
            error.getRejectedValue(),
            new ArrayList<>(Collections.singletonList(error.getDefaultMessage()))
        );
    }
}

