package com.yulcom.inoutfolderapp.application.folders.create.services;

import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilesDeterminator
{
    private final List<FileGenerationStrategy> strategies;

    public List<File> handle(Folder request) {
        List<File> generatedFiles = new ArrayList<>();
        for (FileGenerationStrategy strategy : strategies) {
            if (strategy.shouldExecute(request)) {
                generatedFiles.addAll(strategy.generateFiles(request));
            }
        }
        generatedFiles.forEach(generatedFile -> log.info("Generated file: {}", generatedFile));
        return generatedFiles;
    }


}
