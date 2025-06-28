package com.yulcom.inoutfolderapp.domain.enums;

import lombok.Getter;

@Getter
public enum ProductNature {
    CHEMICAL("chimique"),
    PHARMACEUTICAL("pharmaceutique"),
    FOOD_AND_BEVERAGE("alimentaire"),
    ELECTRONICS("electronique"),
    TEXTILE("textile");

    private final String value;

    ProductNature(String value)
    {
        this.value = value;
    }
}
