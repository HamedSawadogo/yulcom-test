package com.yulcom.inoutfolderapp.commons.dtos.out;

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
public class PaginationMetadata
{
    private int currentPage;
    private int pageSize;
    private long totalPages;
    private long totalItems;

    public static PaginationMetadata toMetaData(int currentPage, int pageSize, long totalPages,
                                                long totalItems)
    {
        return new PaginationMetadata(currentPage, pageSize, totalPages, totalItems);
    }
}
