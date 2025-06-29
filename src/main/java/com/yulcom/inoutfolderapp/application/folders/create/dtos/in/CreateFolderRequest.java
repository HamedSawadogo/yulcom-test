package com.yulcom.inoutfolderapp.application.folders.create.dtos.in;

import com.yulcom.inoutfolderapp.application.dtos.in.CreateProductRequest;
import com.yulcom.inoutfolderapp.domain.enums.Currency;
import com.yulcom.inoutfolderapp.domain.enums.FolderType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateFolderRequest(
    @NotNull
    FolderType type,
    @NotEmpty
    @NotEmpty
    List<CreateProductRequest> products,
    @NotNull
    Currency currency
)
{
}
