package com.yulcom.inoutfolderapp.domain.enums;// package com.inoutfolderapp.domain; // ou le package où tu définis tes enums

import lombok.Getter;

@Getter
public enum ProcessingStructureName
{
    DIRECTION_DES_DOUANES("Direction des Douanes"),
    CHAMBRE_DE_COMMERCE("Chambre de Commerce"),
    BANQUE_PARTENAIRE_AGREE("Banque partenaire agréée"),
    AGENCE_NATIONALE_INSPECTION("Agence nationale d’inspection"),
    MINISTERE_ENVIRONNEMENT("Ministère de l’Environnement"),
    AGENCE_NATIONALE_MEDICAMENT("Agence nationale du médicament"),
    MINISTERE_SANTE("Ministère de la Santé"),
    BCEAO("BCEAO");

    private final String officialName;

    ProcessingStructureName(String officialName)
    {
        this.officialName = officialName;
    }

}
