package com.yulcom.inoutfolderapp.domain.enums;

import lombok.Getter;

@Getter
public enum FileDependencyType
{
    NONE,
    REQUIRED,
    OPTIONAL,
    OR
}
