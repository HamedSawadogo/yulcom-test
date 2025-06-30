package com.yulcom.inoutfolderapp.commons.mappers;

import com.yulcom.inoutfolderapp.commons.dtos.out.GetFileDto;
import com.yulcom.inoutfolderapp.domain.entities.File;

public class FileMapper
{
    public static GetFileDto toResponse(File file) {
        return GetFileDto.builder()
            .assignedHandler(CorporationMapper.toResponse(file.getAssignedHandler()))
            .priority(file.getPriority())
            .status(file.getStatus())
            .build();
    }
}
