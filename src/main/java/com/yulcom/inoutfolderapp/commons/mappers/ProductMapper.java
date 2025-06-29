package com.yulcom.inoutfolderapp.commons.mappers;

import com.yulcom.inoutfolderapp.application.dtos.in.CreateProductRequest;
import com.yulcom.inoutfolderapp.application.dtos.out.GetProductDto;
import com.yulcom.inoutfolderapp.domain.entities.Product;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper
{
    public static Product toEntity(CreateProductRequest request)
    {
        return Product.builder()
            .unitValue(request.unitValue())
            .quantity(request.quantity())
            .designation(request.designation())
            .nature(request.nature())
            .build();
    }

    public static GetProductDto toResponse(Product product) {
        return GetProductDto.builder()
            .unitValue(product.getUnitValue())
            .quantity(product.getQuantity())
            .designation(product.getDesignation())
            .nature(product.getNature())
            .build();
    }

    public static List<Product> toEntityList(List<CreateProductRequest> request)
    {
       return  request.stream().map(ProductMapper::toEntity).collect(Collectors.toList());
    }
}
