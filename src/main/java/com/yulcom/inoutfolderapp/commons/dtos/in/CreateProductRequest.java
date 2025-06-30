package com.yulcom.inoutfolderapp.commons.dtos.in;

import com.yulcom.inoutfolderapp.domain.enums.ProductNature;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;


public record CreateProductRequest(
    @NotBlank
    @NotNull
    String designation,
    @NotBlank
    @NotNull
    ProductNature nature,
    @Positive
    @NotNull
    Integer quantity,
    @Positive
    @NotNull
    BigDecimal unitValue
) {

}
