package com.yulcom.inoutfolderapp.application.users.getfolders.services;

import com.yulcom.inoutfolderapp.application.folders.create.mappers.FolderMapper;
import com.yulcom.inoutfolderapp.application.users.getfolders.dtos.in.GetFoldersRequest;
import com.yulcom.inoutfolderapp.application.users.getfolders.dtos.out.GetFoldersResponseDto;
import com.yulcom.inoutfolderapp.commons.dtos.out.PaginationMetadata;
import com.yulcom.inoutfolderapp.domain.repositories.FolderRepository;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.AuthenticatedUSerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetFolders
{
    private final FolderRepository folderRepository;
    private final AuthenticatedUSerService authenticatedUSerService;

    public GetFoldersResponseDto handle(GetFoldersRequest request) {
        var authenticatedUser =  authenticatedUSerService.getCurrentUser();
        var corporationId = authenticatedUser.getCorporation().getId();
        var pageable = PageRequest.of(request.page(), request.size(), Sort.by(Sort.Direction.DESC, "createdAt"));
        var folders = folderRepository.findAllByCorporationId(corporationId, pageable);
        return GetFoldersResponseDto.builder()
            .items(folders.stream().map(FolderMapper::toResponse).toList())
            .metadata(PaginationMetadata.toMetaData(
                folders.getNumber(),
                folders.getSize(),
                folders.getTotalPages(),
                folders.getTotalElements()
              )
            ).build();
    }
}
