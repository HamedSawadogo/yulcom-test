package com.yulcom.inoutfolderapp.application.dtos.out;

import com.yulcom.inoutfolderapp.domain.enums.ProductNature;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Value;

/**
 * DTO for {@link com.yulcom.inoutfolderapp.domain.entities.Product}
 */
@Value
@Builder
public class GetProductDto implements Serializable
{
    String designation;
    ProductNature nature;
    Integer quantity;
    BigDecimal unitValue;
}
