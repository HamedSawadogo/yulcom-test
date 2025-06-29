package com.yulcom.inoutfolderapp.application.folders.create.services;

import static com.yulcom.inoutfolderapp.domain.enums.FileType.CURRENCY_EXCHANGE_DECLARATION;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.CUSTOMS_CLEARANCE_ATTESTATION;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.FINANCIAL_CAPACITY_ATTESTATION;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.HEALTH_CERTIFICATE;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.IMPORT_DECLARATION;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.MARKETING_AUTHORIZATION;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.ORIGIN_CERTIFICATE;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.PRE_INSPECTION_REPORT;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.PRE_SHIPMENT_INSPECTION;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.SPECIAL_HANDLING_AUTHORIZATION;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.AGENCE_NATIONALE_INSPECTION;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.AGENCE_NATIONALE_MEDICAMENT;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.BANQUE_PARTENAIRE_AGREE;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.BCEAO;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.CHAMBRE_DE_COMMERCE;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.DIRECTION_DES_DOUANES;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.MINISTERE_ENVIRONNEMENT;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.MINISTERE_SANTE;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileAction;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FilePriority;
import com.yulcom.inoutfolderapp.domain.enums.FileStatus;
import com.yulcom.inoutfolderapp.domain.enums.FileType;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName;
import com.yulcom.inoutfolderapp.domain.enums.ProductNature;
import com.yulcom.inoutfolderapp.domain.repositories.CorporationRepository;
import java.time.LocalDateTime;
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
    private final CorporationRepository corporationRepository;

    public List<File> handle(Folder request) {
        boolean containsPharmaceuticals = request.getProducts().stream().anyMatch(p -> p.getNature().equals(ProductNature.PHARMACEUTICAL));
        boolean containsChemicals = request.getProducts().stream().anyMatch(p -> p.getNature().equals(ProductNature.CHEMICAL));
        boolean containsFood = request.getProducts().stream().anyMatch(p -> p.getNature().equals(ProductNature.FOOD_AND_BEVERAGE));
        List<File> generatedFiles = new ArrayList<>();

        if (request.getType().equals(FolderType.IMPORT)) {
            generatedFiles.add(generateFile(PRE_INSPECTION_REPORT, DIRECTION_DES_DOUANES));
            generatedFiles.add(generateFile(IMPORT_DECLARATION, DIRECTION_DES_DOUANES));
        } else if (request.getType().equals(FolderType.EXPORT)) {
            generatedFiles.add(generateFile(ORIGIN_CERTIFICATE, CHAMBRE_DE_COMMERCE));
        }
        if (request.getTotalAmount().doubleValue() >= 10_000_000) {
            generatedFiles.add(generateFile(FINANCIAL_CAPACITY_ATTESTATION, BANQUE_PARTENAIRE_AGREE));
        }
        if (request.getTotalAmount().doubleValue() >= 50_000_000) {
            generatedFiles.add(generateFile(PRE_SHIPMENT_INSPECTION, AGENCE_NATIONALE_INSPECTION));
        }
        if (containsChemicals) {
            generatedFiles.add(generateFile(SPECIAL_HANDLING_AUTHORIZATION, MINISTERE_ENVIRONNEMENT));
        }
        if (containsPharmaceuticals) {
            generatedFiles.add(generateFile(MARKETING_AUTHORIZATION, AGENCE_NATIONALE_MEDICAMENT));
        }
        if (containsChemicals) {
            generatedFiles.add(generateFile(SPECIAL_HANDLING_AUTHORIZATION, MINISTERE_ENVIRONNEMENT));
        }
        if (containsPharmaceuticals) {
            generatedFiles.add(generateFile(MARKETING_AUTHORIZATION, AGENCE_NATIONALE_MEDICAMENT));
        }
        if (containsFood) {
            generatedFiles.add(generateFile(HEALTH_CERTIFICATE, MINISTERE_SANTE));
        }
        if (!request.getCurrency().equals(Currency.XOF)) {
            generatedFiles.add(generateFile(CURRENCY_EXCHANGE_DECLARATION, BCEAO));
        }
        generatedFiles.add(generateFile(CUSTOMS_CLEARANCE_ATTESTATION, DIRECTION_DES_DOUANES));
        generatedFiles.forEach(generatedFile -> log.info("Generated file: {}", generatedFile));
        return generatedFiles;
    }

    private File generateFile(FileType fileType, ProcessingStructureName processingStructureName) {
        var file = new File();
        file.setType(fileType);
        file.setStatus(FileStatus.PENDING);
        file.setPriority(FilePriority.NORMAL);
        file.setAssignedHandler(corporationRepository.findByName(processingStructureName.getOfficialName()).orElse(null));
        file.addAction(new FileAction(null, LocalDateTime.now()));
        file.setCreatedAt(LocalDateTime.now());
        return file;
    }
}
