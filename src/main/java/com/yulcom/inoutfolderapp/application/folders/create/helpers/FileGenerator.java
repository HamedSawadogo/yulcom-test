package com.yulcom.inoutfolderapp.application.folders.create.helpers;

import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileAction;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
import com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName;
import com.yulcom.inoutfolderapp.domain.repositories.CorporationRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileGenerator
{
    private final CorporationRepository corporationRepository;

    public File generateFile(FileType fileType,
                             ProcessingStructureName processingStructureName,
                             int filePriority
    )
    {
        var file = new File();
        file.setType(fileType.getDescription());
        file.setPriority(filePriority);
        file.setIsEligible(filePriority == 1); //must important priority
        file.setStatus(FileStatus.PENDING);
        file.setAssignedHandler(corporationRepository.findByName(processingStructureName.getOfficialName()).orElse(null));
        file.addAction(new FileAction(null, LocalDateTime.now()));
        file.setCreatedAt(LocalDateTime.now());
        return file;
    }

    public List<File> generateDependencies(File file)
    {
        List<File> files = new ArrayList<>();
        for (var dependency : file.getDependencies()) {
            var processingStructureName = file.getAssignedHandler() != null ? file.getAssignedHandler().getName() : null;
            int priority = 2;//how calculate
            files.add(generateFile(FileType.valueOf(dependency.getName()), ProcessingStructureName.valueOf(processingStructureName), priority));
        }
        return files;
    }
}
