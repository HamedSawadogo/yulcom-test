package com.yulcom.inoutfolderapp.domain.exceptions.job;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DependenciesNotSignedException extends RuntimeException
{
    public DependenciesNotSignedException(String message)
    {
        super(message);
    }
}
