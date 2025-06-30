package com.yulcom.inoutfolderapp.commons.dtos.out;

import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import java.io.Serializable;
import lombok.Builder;
import lombok.Value;

/**
 * DTO for {@link com.yulcom.inoutfolderapp.domain.entities.File}
 */
@Value
@Builder
public class GetFileDto implements Serializable
{
    String type;
    Integer priority;
    FileStatus status;
    GetCorporationDto assignedHandler;


}
