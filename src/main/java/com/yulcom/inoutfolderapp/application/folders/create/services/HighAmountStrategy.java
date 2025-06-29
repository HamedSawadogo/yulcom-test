package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;

import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HighAmountStrategy implements FileGenerationStrategy {
    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder) {
        if (folder.getTotalAmount().doubleValue() >= 50_000_000) {
            return List.of(
                fileGenerator.generateFile(FINANCIAL_CAPACITY_ATTESTATION, BANQUE_PARTENAIRE_AGREE),
                fileGenerator.generateFile(PRE_SHIPMENT_INSPECTION, AGENCE_NATIONALE_INSPECTION)
            );
        } else if (folder.getTotalAmount().doubleValue() >= 10_000_000) {
            return List.of(
                fileGenerator.generateFile(FINANCIAL_CAPACITY_ATTESTATION, BANQUE_PARTENAIRE_AGREE)
            );
        }
        return List.of();
    }

    @Override
    public boolean shouldExecute(Folder folder) {
        return folder.getTotalAmount().doubleValue() >= 10_000_000;
    }
}
