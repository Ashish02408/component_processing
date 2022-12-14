package com.ashish.component_processing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PackagingAndDeliveryServiceException extends RuntimeException {
    public PackagingAndDeliveryServiceException(String msg) {
        super(msg);
    }
}
