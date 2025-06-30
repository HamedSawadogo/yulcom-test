package com.yulcom.inoutfolderapp.commons.dtos.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.yulcom.inoutfolderapp.domain.exceptions.validations.ValidationErrorData;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record HttpErrorResponse(
    int status,
    String code,
    String errorMessage,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<ValidationErrorData> validationErrors,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String exception,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Error> errors,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateTime,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String path
) { }
