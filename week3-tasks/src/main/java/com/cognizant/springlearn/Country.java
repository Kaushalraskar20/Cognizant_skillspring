package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Country - model class mapped to country beans in country.xml.
 *
 * Hands-on 4: plain Java class so Spring XML can instantiate it
 *             and inject code/name via setter injection.
 * Hands-on doc 4 (POST validation): @NotNull and @Size trigger
 *             Bean Validation when @Valid is on the controller param.
 */
public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    @NotNull(message = "Country code must not be null")
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    private String code;

    @NotNull(message = "Country name must not be null")
    private String name;

    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    public String getCode() {
        LOGGER.debug("Inside getCode. code={}", code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside setCode. code={}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside getName. name={}", name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName. name={}", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
