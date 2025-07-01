package com.yulcom.inoutfolderapp.application.folders.create.dtos.out;

import com.yulcom.inoutfolderapp.commons.dtos.out.GetFileDto;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import java.util.List;
import java.util.UUID;
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
   private UUID id;
   private FolderType type;
   private Currency currency;
   private List<GetFileDto> files;
}
