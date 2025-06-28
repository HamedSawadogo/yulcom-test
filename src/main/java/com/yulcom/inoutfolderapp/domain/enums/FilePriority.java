package com.yulcom.inoutfolderapp.domain.enums;

import lombok.Getter;

@Getter
public enum FilePriority {
    LOW(3),
    NORMAL(2),
    HIGH(1),
    URGENT(0);

    private final int value;

    FilePriority(int value)
    {
        this.value = value;
    }
}
