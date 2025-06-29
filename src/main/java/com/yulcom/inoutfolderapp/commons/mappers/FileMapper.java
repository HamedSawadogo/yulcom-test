package com.yulcom.inoutfolderapp.commons.mappers;

import com.yulcom.inoutfolderapp.application.dtos.out.GetFileDto;
import com.yulcom.inoutfolderapp.domain.entities.File;

public class FileMapper
{
    public static GetFileDto toResponse(File file) {
        return GetFileDto.builder()
            .assignedHandler(CorporationMapper.toResponse(file.getAssignedHandler()))
            .type(file.getType())
            .priority(file.getPriority())
            .status(file.getStatus())
            .build();
    }
}
