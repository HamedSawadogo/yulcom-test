package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;

import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForeignCurrencyStrategy implements FileGenerationStrategy {

    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder) {
        var file  = fileGenerator.generateFile(CURRENCY_EXCHANGE_DECLARATION, BCEAO, 1);
        file.addDependency(new FileDependency(FINANCIAL_CAPACITY_ATTESTATION.getDescription(), FileDependencyType.OPTIONAL));
        return List.of(file);
    }

    @Override
    public boolean shouldExecute(Folder folder) {
        return !folder.getCurrency().equals(Currency.XOF);
    }
}
