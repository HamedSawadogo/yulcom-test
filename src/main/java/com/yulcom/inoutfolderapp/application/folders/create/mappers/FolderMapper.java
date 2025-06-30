package com.yulcom.inoutfolderapp.application.folders.create.mappers;

import com.yulcom.inoutfolderapp.application.folders.create.dtos.in.CreateFolderRequest;
import com.yulcom.inoutfolderapp.application.folders.create.dtos.out.GetFolderResponse;
import com.yulcom.inoutfolderapp.commons.mappers.FileMapper;
import com.yulcom.inoutfolderapp.domain.entities.Folder;

public class FolderMapper
{
    public static Folder.FolderBuilder toEntity(CreateFolderRequest request)
    {
        return Folder.builder()
            .type(request.type())
            .currency(request.currency());
    }

    public static GetFolderResponse toResponse(Folder folder)
    {
        return GetFolderResponse.builder()
            .type(folder.getType())
            .files(folder.getFiles().stream().map(FileMapper::toResponse).toList())
            .currency(folder.getCurrency())
            .build();
    }
}
