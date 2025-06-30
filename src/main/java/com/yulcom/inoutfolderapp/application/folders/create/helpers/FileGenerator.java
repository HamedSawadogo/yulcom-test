package com.yulcom.inoutfolderapp.application.folders.create.helpers;

import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileAction;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
import com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName;
import com.yulcom.inoutfolderapp.domain.repositories.CorporationRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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
    public List<File> generateDependencies(File file, Map<String, File> filesByType) {
        List<File> files = new ArrayList<>();

        for (var dependency : file.getDependencies()) {
            String dependencyName = dependency.getName();
            log.info("Generating dependencies for {}", dependencyName);
            File depFile = filesByType.get(dependencyName);
            int depPriority = (depFile != null && depFile.getPriority() != null) ? depFile.getPriority() : 1; // fallback si jamais pas trouvé
            int priority = depPriority + 1;
            var processingStructureName = file.getAssignedHandler() != null ? file.getAssignedHandler().getName() : null;

            File generatedFile = generateFile(
                Objects.requireNonNull(Arrays.stream(FileType.values()).filter(fileType -> fileType.getDescription().equals(dependencyName)).findFirst().orElse(null)),
                Objects.requireNonNull(Arrays.stream(ProcessingStructureName.values()).filter(name -> name.getOfficialName().equals(processingStructureName)).findFirst().orElse(null)),
                priority
            );
            files.add(generatedFile);
            filesByType.put(dependencyName, generatedFile);
        }
        return files;
    }
}
