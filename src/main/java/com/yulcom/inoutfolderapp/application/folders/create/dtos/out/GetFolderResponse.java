package com.yulcom.inoutfolderapp.application.folders.create.dtos.out;

import com.yulcom.inoutfolderapp.application.dtos.products.out.GetCorporationResponse;
import com.yulcom.inoutfolderapp.application.dtos.products.out.GetProductResponse;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetFolderResponse {
   private FolderType type;
   private GetCorporationResponse corporation;
   private List<GetProductResponse> products;
   private Currency currency;
}
