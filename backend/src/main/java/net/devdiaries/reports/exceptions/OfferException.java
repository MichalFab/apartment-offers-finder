package net.devdiaries.reports.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferException extends RuntimeException {
    public OfferException(String message) {
        super(message);
    }
}