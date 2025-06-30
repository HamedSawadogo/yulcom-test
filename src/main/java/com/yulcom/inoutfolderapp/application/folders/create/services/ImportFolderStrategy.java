package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;
import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportFolderStrategy implements FileGenerationStrategy
{
    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder)
    {
        final  int filePriority = 1;
        File preInspectionReport = fileGenerator.generateFile(PRE_INSPECTION_REPORT, DIRECTION_DES_DOUANES, filePriority);
        File importDeclaration = fileGenerator.generateFile(IMPORT_DECLARATION, DIRECTION_DES_DOUANES, filePriority);
        importDeclaration.addDependency(new FileDependency(PRE_INSPECTION_REPORT.getDescription(), FileDependencyType.OR));
        return List.of(preInspectionReport, importDeclaration);
    }


    @Override
    public boolean shouldExecute(Folder folder)
    {
        return folder.getType().equals(FolderType.IMPORT);
    }
}
