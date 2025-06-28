package com.yulcom.inoutfolderapp.domain.exceptions.validations.errors;

import lombok.Builder;

@Builder
public record ValidationError(String field, String error)
{
}
