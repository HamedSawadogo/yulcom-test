package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;
import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomsClearanceStrategy implements FileGenerationStrategy {
    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder) {
        final  int filePriority = 1;
        var file = fileGenerator.generateFile(CUSTOMS_CLEARANCE_ATTESTATION, DIRECTION_DES_DOUANES, filePriority);
        var allFilesWithoutHealthCertificate = Arrays.stream(FileType.values()).filter(fileType -> fileType.equals(HEALTH_CERTIFICATE)).toList();
        allFilesWithoutHealthCertificate.forEach(fileType -> file.addDependency(new FileDependency(fileType.getDescription(), FileDependencyType.REQUIRED)));
        return List.of(file);
    }


    @Override
    public boolean shouldExecute(Folder folder) {
        return true;
    }
}
