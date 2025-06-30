package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;
import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportFolderStrategy implements FileGenerationStrategy {
    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder) {
        final  int filePriority = 1;
        List<File> baseFiles = new ArrayList<>();
        baseFiles.add(fileGenerator.generateFile(ORIGIN_CERTIFICATE, CHAMBRE_DE_COMMERCE, filePriority));
        return baseFiles;
    }


    @Override
    public boolean shouldExecute(Folder folder) {
        return folder.getType().equals(FolderType.EXPORT);
    }
}
