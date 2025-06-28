package com.yulcom.inoutfolderapp.domain.enums;

import lombok.Getter;

@Getter
public enum FileType {
    PRE_INSPECTION_REPORT("Contrôle préalable de marchandise"),
    IMPORT_DECLARATION("Déclaration d’importation"),
    ORIGIN_CERTIFICATE("Certificat d’origine"),
    FINANCIAL_CAPACITY_ATTESTATION("Attestation de capacité financière"),
    PRE_SHIPMENT_INSPECTION("Inspection obligatoire avant embarquement"),
    SPECIAL_HANDLING_AUTHORIZATION("Autorisation spéciale de manipulation"),
    MARKETING_AUTHORIZATION("Autorisation de mise sur le marché"),
    HEALTH_CERTIFICATE("Certificat sanitaire"),
    CURRENCY_EXCHANGE_DECLARATION("Déclaration d’échange de devise"),
    CUSTOMS_CLEARANCE_ATTESTATION("Attestation de dédouanement");

    private final String description;

    FileType(String description) {
        this.description = description;
    }

}
