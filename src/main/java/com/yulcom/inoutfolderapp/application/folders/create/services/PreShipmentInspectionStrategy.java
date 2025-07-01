package com.yulcom.inoutfolderapp.application.folders.create.services;
import static com.yulcom.inoutfolderapp.domain.enums.Currency.CNY;
import static com.yulcom.inoutfolderapp.domain.enums.Currency.EUR;
import static com.yulcom.inoutfolderapp.domain.enums.Currency.GBP;
import static com.yulcom.inoutfolderapp.domain.enums.Currency.JPY;
import static com.yulcom.inoutfolderapp.domain.enums.Currency.USD;
import static com.yulcom.inoutfolderapp.domain.enums.Currency.XOF;
import static com.yulcom.inoutfolderapp.domain.enums.FileType.*;
import static com.yulcom.inoutfolderapp.domain.enums.ProcessingStructureName.*;
import com.yulcom.inoutfolderapp.application.folders.create.helpers.FileGenerator;
import com.yulcom.inoutfolderapp.domain.entities.File;
import com.yulcom.inoutfolderapp.domain.entities.FileDependency;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FileDependencyType;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreShipmentInspectionStrategy implements FileGenerationStrategy {

    private final FileGenerator fileGenerator;
    private static final Map<Currency, Double> XOF_CONVERSION_RATES = new EnumMap<>(Currency.class);

    static {
        XOF_CONVERSION_RATES.put(XOF, 1.0);
        XOF_CONVERSION_RATES.put(EUR, 655.957);
        XOF_CONVERSION_RATES.put(USD, 600.0);
        XOF_CONVERSION_RATES.put(GBP, 750.0);
        XOF_CONVERSION_RATES.put(JPY, 4.5);
        XOF_CONVERSION_RATES.put(CNY, 85.0);
    }

    @Override
    public List<File> generateFiles(Folder folder) {
        double amount = folder.getTotalAmount().doubleValue() * XOF_CONVERSION_RATES.get(folder.getCurrency());
        log.info("Amount in XOF: {}", amount);

        if (amount >= 50_000_000.) {
            var file = fileGenerator.generateFile(PRE_SHIPMENT_INSPECTION, AGENCE_NATIONALE_INSPECTION, 4);
            file.addDependency(new FileDependency(FINANCIAL_CAPACITY_ATTESTATION.getDescription(), FileDependencyType.REQUIRED));
            return List.of(file);
        }
        return List.of();
    }

    @Override
    public boolean shouldExecute(Folder folder) {
        double amount = folder.getTotalAmount().doubleValue() * XOF_CONVERSION_RATES.get(folder.getCurrency());
        return amount >= 50_000_000.;
    }
}
