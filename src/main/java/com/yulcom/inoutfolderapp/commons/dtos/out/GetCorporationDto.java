package com.yulcom.inoutfolderapp.commons.dtos.out;

import com.yulcom.inoutfolderapp.domain.enums.CorporationType;
import java.io.Serializable;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetCorporationDto implements Serializable
{
    String name;
    CorporationType type;
}
