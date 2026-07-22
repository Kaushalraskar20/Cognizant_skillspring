package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CountryService — business logic for country operations.
 *
 * Loads the country list from country.xml on each call.
 * In a real application this data would come from a database
 * (see orm-learn / Week 2 for the JPA version).
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    /** Returns all countries from country.xml. */
    @SuppressWarnings("unchecked")
    public List<Country> getCountryList() {
        LOGGER.info("Start getCountryList");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", List.class);
        LOGGER.debug("countries={}", countries);
        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End getCountryList");
        return countries;
    }

    /**
     * Returns the country whose code matches (case-insensitive).
     * Uses Java 8 Stream + lambda — no explicit for-loop needed.
     *
     * @throws CountryNotFoundException when no match is found (→ HTTP 404).
     */
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start getCountry, code={}", code);

        Country found = getCountryList().stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);

        if (found == null) {
            LOGGER.warn("Country not found for code={}", code);
            throw new CountryNotFoundException(code);
        }

        LOGGER.debug("Found: {}", found);
        LOGGER.info("End getCountry");
        return found;
    }
}
