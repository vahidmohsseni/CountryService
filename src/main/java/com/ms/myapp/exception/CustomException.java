package com.ms.myapp.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component
public class CustomException extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttr = super.getErrorAttributes(request, options);
        Throwable throwable = getError(request);
        if (throwable instanceof MyNFException){
            ResponseStatusException rx = (MyNFException) throwable;
            errorAttr.put("message", "Not Found");
            return errorAttr;
        }
        return errorAttr;

    }
}
