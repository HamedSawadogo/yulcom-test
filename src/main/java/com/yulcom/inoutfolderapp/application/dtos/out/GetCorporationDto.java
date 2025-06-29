package com.yulcom.inoutfolderapp.application.dtos.out;

import com.yulcom.inoutfolderapp.domain.enums.CorporationType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetCorporationDto implements Serializable
{
    LocalDateTime updatedAt;
    String name;
    CorporationType type;
    String description;
}
