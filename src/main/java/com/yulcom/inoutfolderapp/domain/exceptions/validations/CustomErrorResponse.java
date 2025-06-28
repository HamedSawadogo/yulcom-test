package com.yulcom.inoutfolderapp.domain.exceptions.validations;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record CustomErrorResponse(
    LocalDateTime timestamp,
    List<Error> errors
)
{
}
