package com.yulcom.inoutfolderapp.commons.mappers;

import com.yulcom.inoutfolderapp.commons.dtos.in.CreateProductRequest;
import com.yulcom.inoutfolderapp.domain.entities.Product;
import java.util.List;

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

    public static List<Product> toEntityList(List<CreateProductRequest> request)
    {
       return  request.stream().map(ProductMapper::toEntity).toList();
    }
}
