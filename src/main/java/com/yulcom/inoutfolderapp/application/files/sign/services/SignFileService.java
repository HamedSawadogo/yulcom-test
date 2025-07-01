package com.yulcom.inoutfolderapp.application.files.sign.services;

import com.yulcom.inoutfolderapp.application.files.getallbyfolder.dtos.out.GetFileDto;
import com.yulcom.inoutfolderapp.commons.mappers.FileMapper;
import com.yulcom.inoutfolderapp.domain.entities.FileAction;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.enums.FileActionType;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.exceptions.helpers.ErrorMessagesHelpers;
import com.yulcom.inoutfolderapp.domain.exceptions.job.DependenciesNotSignedException;
import com.yulcom.inoutfolderapp.domain.exceptions.job.FileNotFoundException;
import com.yulcom.inoutfolderapp.domain.repositories.FileDependencyRepository;
import com.yulcom.inoutfolderapp.domain.repositories.FileRepository;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.AuthenticatedUSerService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class SignFileService
{
    private final FileDependencyRepository fileDependencyRepository;
    private final FileRepository fileRepository;
    private final AuthenticatedUSerService authenticatedUSerService;

    @PreAuthorize("hasAuthority('FILE_SIGNER')")
    @Transactional
    public GetFileDto handle(UUID fileId)
    {
        var authenticatedUser = authenticatedUSerService.getCurrentUser();
        var file = fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
        if (file.getStatus().equals(FileStatus.SIGNED))
        {
            return FileMapper.toResponse(file);
        }
        var dependencies = fileDependencyRepository.findAllByFileParentId(fileId).stream().map(FileDependency::getFileParent).toList();
        boolean hasAllDependenciesSigned = dependencies.stream().allMatch(fileDependency -> fileDependency.getStatus().equals(FileStatus.SIGNED));
        if (!hasAllDependenciesSigned)
        {
            throw new DependenciesNotSignedException(ErrorMessagesHelpers.DEPENDENCIES_NOT_SIGNED_EXCEPTION_MESSAGE);
        }
        file.setStatus(FileStatus.SIGNED);
        file.addAction(new FileAction(authenticatedUser, LocalDateTime.now(), FileActionType.SIGN));
        fileRepository.save(file);
        return FileMapper.toResponse(file);
    }
}
