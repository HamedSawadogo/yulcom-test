package com.yulcom.inoutfolderapp.application.folders.create.services;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilesDeterminator
{
    private final List<FileGenerationStrategy> strategies;

    public List<File> handle(Folder folder)
    {
        List<File> generatedFiles = new ArrayList<>();
        Map<String, File> filesByType = new HashMap<>();

        for (FileGenerationStrategy strategy : strategies)
        {
            if (strategy.shouldExecute(folder))
            {
                List<File> files = strategy.generateFiles(folder);
                for (File file : files)
                {
                    filesByType.put(file.getType(), file);
                }
                generatedFiles.addAll(files);
            }
        }
        for (var file : generatedFiles)
        {
            var computedPriority = computePriority(file, filesByType);
            file.setPriority(computedPriority);
        }
        generatedFiles.forEach(f -> log.info("Generated file: {}", f));
        return generatedFiles;
    }

    private int computePriority(File file, Map<String, File> filesByType)
    {
        int maxDependencyPriority = 1;
        if (file.getDependencies() == null || file.getDependencies().isEmpty())
        {
            return maxDependencyPriority;
        }
        for (var dependency : file.getDependencies())
        {
            var depFile = filesByType.get(dependency.getName());
            if (dependency.getDependencyType().equals(FileDependencyType.OR) && depFile == null)
            {
                continue;
            }
            if (depFile != null)
            {
                int depPriority = computePriority(depFile, filesByType);
                maxDependencyPriority = Math.max(maxDependencyPriority, depPriority);
            }
        }
        if (file.getType().equals(FileType.CUSTOMS_CLEARANCE_ATTESTATION.getDescription()))  {
            return maxDependencyPriority + 2;
        }


        return maxDependencyPriority + 1;
    }
}
