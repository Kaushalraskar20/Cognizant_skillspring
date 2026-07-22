package com.cognizant.springlearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CountryNotFoundException — thrown when no country matches the given code.
 *
 * @ResponseStatus causes Spring to return HTTP 404 automatically whenever
 * this exception propagates out of a controller method.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends Exception {

    public CountryNotFoundException(String code) {
        super("No country found with code: " + code);
    }
}
