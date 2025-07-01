package com.yulcom.inoutfolderapp.application.folders.getall.dtos.out;

import com.yulcom.inoutfolderapp.application.folders.create.dtos.out.GetFolderResponse;
import com.yulcom.inoutfolderapp.commons.dtos.out.PaginationMetadata;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFoldersResponseDto
{
    private List<GetFolderResponse> items;
    private PaginationMetadata metadata;
}
