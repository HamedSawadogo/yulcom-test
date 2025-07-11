package com.yulcom.inoutfolderapp.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends RuntimeException
{
    public AccessDeniedException()
    {
        super("user bad credentials");
    }
}
