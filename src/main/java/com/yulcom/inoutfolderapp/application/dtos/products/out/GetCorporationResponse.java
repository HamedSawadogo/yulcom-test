package com.yulcom.inoutfolderapp.application.dtos.products.out;

import com.yulcom.inoutfolderapp.domain.enums.CorporationType;
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
public class GetCorporationResponse
{
    private String name;
    private CorporationType type;
}
