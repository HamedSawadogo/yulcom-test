package com.yulcom.inoutfolderapp.domain.exceptions.job;

import com.yulcom.inoutfolderapp.domain.exceptions.helpers.ErrorMessagesHelpers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException
{
    public FileNotFoundException()
    {
        super(ErrorMessagesHelpers.FILE_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
