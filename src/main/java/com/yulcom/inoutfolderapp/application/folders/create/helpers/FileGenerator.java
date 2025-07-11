package com.yulcom.inoutfolderapp.application.folders.create.helpers;

import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileAction;
import com.yulcom.inoutfolderapp.domain.enums.FileActionType;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
import com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName;
import com.yulcom.inoutfolderapp.domain.repositories.CorporationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileGenerator
{
    private final CorporationRepository corporationRepository;

    public File generateFile(
        FileType fileType,
        ProcessingStructureName processingStructureName,
        int filePriority
    )
    {
        var file = new File();
        file.setType(fileType.getDescription());
        file.setPriority(filePriority);
        file.setIsEligible(filePriority == 1);
        file.setStatus(FileStatus.PENDING);
        file.setAssignedHandler(corporationRepository.findByName(processingStructureName.getOfficialName()).orElse(null));
        file.addAction(new FileAction(null, LocalDateTime.now(), FileActionType.CREATE));
        file.setCreatedAt(LocalDateTime.now());
        return file;
    }

}
