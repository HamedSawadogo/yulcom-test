package com.yulcom.inoutfolderapp.application.folders.create.services;
import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
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
    private final FileGenerator fileGenerator;

    public List<File> handle(Folder folder) {
        List<File> generatedFiles = new ArrayList<>();
        Map<String, File> filesByType = new HashMap<>();

        for (FileGenerationStrategy strategy : strategies) {
            if (strategy.shouldExecute(folder)) {
                List<File> files = strategy.generateFiles(folder);
                for (File file : files) {
                    filesByType.put(file.getType(), file);
                }

                generatedFiles.addAll(files);
                for (File file : files) {
                    List<File> deps = fileGenerator.generateDependencies(file, filesByType);
                    generatedFiles.addAll(deps);
                }
            }
        }
        generatedFiles.forEach(f -> log.info("Generated file: {}", f));
        return generatedFiles;
    }

}
