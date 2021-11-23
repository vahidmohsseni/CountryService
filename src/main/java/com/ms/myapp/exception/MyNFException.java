package com.ms.myapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MyNFException extends ResponseStatusException {

    public MyNFException(HttpStatus status, String reason) {
        super(status, reason);
    }

}
