package com.cms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 23.07.2019
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
public class CmsRecordNotFoundException extends RuntimeException {
    boolean resourceName;
    String fieldName;

    public CmsRecordNotFoundException(boolean resourceName, String fieldName) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
