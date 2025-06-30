package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;
import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.ProductNature;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodProductsStrategy implements FileGenerationStrategy {
    private final FileGenerator fileGenerator;

    @Override
    public List<File> generateFiles(Folder folder) {
        return List.of(fileGenerator.generateFile(HEALTH_CERTIFICATE, MINISTERE_SANTE, 1));
    }


    @Override
    public boolean shouldExecute(Folder folder) {
        return folder.getProducts().stream()
            .anyMatch(p -> p.getNature().equals(ProductNature.FOOD_AND_BEVERAGE));
    }
}
