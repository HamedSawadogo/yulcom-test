package com.yulcom.inoutfolderapp.application.folders.create.dtos;

import com.yulcom.inoutfolderapp.application.dtos.products.in.CreateProductRequest;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record CreateFolderRequest(
    @NotNull
    FolderType type,
    @NotNull
    UUID corporationId,
    @NotEmpty
    @NotEmpty
    List<CreateProductRequest> products,
    @NotNull
    Currency currency
)
{
}
