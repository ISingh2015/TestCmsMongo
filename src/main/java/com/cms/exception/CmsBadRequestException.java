package com.cms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CmsBadRequestException extends Exception{

    public CmsBadRequestException(String message) {
        super(message);
    }

    public CmsBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
