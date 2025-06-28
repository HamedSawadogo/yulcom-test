package com.yulcom.inoutfolderapp.application.dtos.products.out;

import com.yulcom.inoutfolderapp.domain.enums.ProductNature;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetProductResponse
{
    private String designation;
    private ProductNature nature;
    private Integer quantity;
    private BigDecimal unitValue;
}
