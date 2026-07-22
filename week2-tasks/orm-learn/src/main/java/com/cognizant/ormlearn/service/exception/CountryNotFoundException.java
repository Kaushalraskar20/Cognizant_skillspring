package com.cognizant.ormlearn.service.exception;

/**
 * CountryNotFoundException - thrown when a lookup by country code yields no result.
 *
 * Hands-on 1 (find by code): Using a custom exception rather than returning null
 * forces the caller to explicitly handle the "not found" case, making the API
 * contract clear and avoiding NullPointerExceptions in calling code.
 */
public class CountryNotFoundException extends Exception {

    public CountryNotFoundException(String countryCode) {
        super("Country not found for code: " + countryCode);
    }
}
