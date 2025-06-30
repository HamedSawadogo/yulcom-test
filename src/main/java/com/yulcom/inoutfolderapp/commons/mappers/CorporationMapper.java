package com.yulcom.inoutfolderapp.commons.mappers;

import com.yulcom.inoutfolderapp.commons.dtos.out.GetCorporationDto;
import com.yulcom.inoutfolderapp.domain.entities.Corporation;

public class CorporationMapper
{
    public static GetCorporationDto toResponse(Corporation corporation)
    {
        return GetCorporationDto.builder()
            .type(corporation.getType())
            .name(corporation.getName())
            .build();
    }
}
