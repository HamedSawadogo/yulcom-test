package com.yulcom.inoutfolderapp.foders.its;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulcom.inoutfolderapp.application.folders.create.dtos.in.CreateFolderRequest;
import com.yulcom.inoutfolderapp.commons.dtos.in.CreateProductRequest;
import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import com.yulcom.inoutfolderapp.domain.enums.ProductNature;
import com.yulcom.inoutfolderapp.domain.repositories.FolderRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CreateFolderControllerIT
{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FolderRepository folderRepository;


    @Test
    @WithMockUser(authorities = "FILE_CREATOR")
    void shouldCreateFolderSuccessfully() throws Exception
    {
        var products = List.of(
            new CreateProductRequest("Acide Sulfurique", ProductNature.CHEMICAL, 1000,
                BigDecimal.valueOf(500)),
            new CreateProductRequest("Kit de test COVID-19", ProductNature.PHARMACEUTICAL, 500,
                BigDecimal.valueOf(50.60)),
            new CreateProductRequest("Riz Basmati 5kg", ProductNature.FOOD_AND_BEVERAGE, 200,
                BigDecimal.valueOf(200)),
            new CreateProductRequest("Smartphone X", ProductNature.ELECTRONICS, 50,
                BigDecimal.valueOf(7650))
        );
        var request = new CreateFolderRequest(FolderType.IMPORT, products, Currency.EUR);

        MvcResult mvcResult = mockMvc.perform(post("/folders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().json(
                """
                 {
                   "id": "8ffa46ad-bb84-40cc-900d-3fe5393fd0e5",
                   "type": "IMPORT",
                   "currency": "EUR",
                   "files": [
                     {
                       "type": "Attestation de dédouanement",
                       "priority": 4,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Direction des Douanes",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Attestation de capacité financière",
                       "priority": 3,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Banque partenaire agréée",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Certificat sanitaire",
                       "priority": 3,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Ministère de la Santé",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Déclaration d’échange de devise",
                       "priority": 4,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "BCEAO",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Contrôle préalable de marchandise",
                       "priority": 1,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Direction des Douanes",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Déclaration d’importation",
                       "priority": 2,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Direction des Douanes",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Autorisation de mise sur le marché",
                       "priority": 3,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Agence nationale du médicament",
                         "type": "PROCESSING_AGENCY"
                       }
                     },
                     {
                       "type": "Inspection obligatoire avant embarquement",
                       "priority": 4,
                       "status": "PENDING",
                       "assignedHandler": {
                         "name": "Agence nationale d’inspection",
                         "type": "PROCESSING_AGENCY"
                       }
                     }
                   ]
                 }


              """
            ))
            .andDo(print()).andReturn();
        var response = new JSONObject(mvcResult.getResponse().getContentAsString());
        var folderId = response.get("id").toString();
        Folder folder = folderRepository.findById(UUID.fromString(folderId)).orElseThrow();
        assertThat(folder.getTotalAmount()).isEqualTo(BigDecimal.valueOf(947800.0));
    }
}
