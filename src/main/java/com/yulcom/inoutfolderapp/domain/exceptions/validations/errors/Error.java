package com.yulcom.inoutfolderapp.domain.exceptions.validations.errors;

import lombok.Builder;

@Builder
public record Error(
    String field,
    String messageCode,
    String error,
    int status
) { }
