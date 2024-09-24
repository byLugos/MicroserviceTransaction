package com.transactions.supplies.infraestructure.feing.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class CustomErrorEncoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException("Bad Request error from microservice");
            case 404 -> new ChangeSetPersister.NotFoundException();
            case 500 -> new InternalAuthenticationServiceException("Internal Server Error in microservice");
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }
}
