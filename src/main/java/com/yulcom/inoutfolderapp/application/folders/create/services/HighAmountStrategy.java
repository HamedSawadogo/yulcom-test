package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;

import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HighAmountStrategy implements FileGenerationStrategy {
    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder) {
        final  int filePriority = 1;
        if (folder.getTotalAmount().doubleValue() >= 50_000_000) {
            var financialFile = fileGenerator.generateFile(PRE_SHIPMENT_INSPECTION, AGENCE_NATIONALE_INSPECTION, filePriority);
            financialFile.addDependency(new FileDependency(FINANCIAL_CAPACITY_ATTESTATION.getDescription(), FileDependencyType.REQUIRED));
            return List.of(financialFile);
        } else if (folder.getTotalAmount().doubleValue() >= 10_000_000) {
            var financialFile = fileGenerator.generateFile(FINANCIAL_CAPACITY_ATTESTATION, BANQUE_PARTENAIRE_AGREE, filePriority);
            financialFile.addDependency(new FileDependency(IMPORT_DECLARATION.getDescription(), FileDependencyType.OR));
            financialFile.addDependency(new FileDependency(ORIGIN_CERTIFICATE.getDescription(), FileDependencyType.OR));
            return List.of(financialFile);
        }
        return List.of();
    }

    @Override
    public boolean shouldExecute(Folder folder) {
        return folder.getTotalAmount().doubleValue() >= 10_000_000;
    }
}
