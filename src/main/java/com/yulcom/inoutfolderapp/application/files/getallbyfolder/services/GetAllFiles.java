package com.yulcom.inoutfolderapp.application.files.getallbyfolder.services;

import com.yulcom.inoutfolderapp.application.files.getallbyfolder.dtos.out.GetFileDto;
import com.yulcom.inoutfolderapp.commons.mappers.FileMapper;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.repositories.FileDependencyRepository;
import com.yulcom.inoutfolderapp.domain.repositories.FileRepository;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.AuthenticatedUSerService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAllFiles
{
    private final FileRepository fileRepository;
    private final AuthenticatedUSerService authenticatedUSerService;
    private final FileDependencyRepository fileDependencyRepository;

    @PreAuthorize("hasAuthority('VIEW_FILE')")
    @Transactional(readOnly = true)
    public List<GetFileDto> handle(UUID folderId)
    {
        var authenticatedUser = authenticatedUSerService.getCurrentUser();
        var files = fileRepository.findAllByAssociatedFolderIdAndAssignedHandlerId(folderId, authenticatedUser.getId());
        files.forEach(file -> file.setIsEligible(isEligible(file.getId())));
        return files.stream().map(FileMapper::toResponse).toList();
    }

    private boolean isEligible(UUID fileId) {
        var dependencies = fileDependencyRepository.findAllByFileParentId(fileId).stream().map(FileDependency::getFileParent).toList();
        return dependencies.stream().allMatch(fileDependency -> fileDependency.getStatus().equals(FileStatus.SIGNED));
    }

}
