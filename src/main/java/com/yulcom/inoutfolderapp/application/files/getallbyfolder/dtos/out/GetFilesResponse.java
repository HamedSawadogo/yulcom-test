package com.yulcom.inoutfolderapp.application.files.getallbyfolder.dtos.out;

import com.yulcom.inoutfolderapp.commons.dtos.out.PaginationMetadata;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetFilesResponse
{
    private List<GetFileDto> items;
    private PaginationMetadata metadata;
}
