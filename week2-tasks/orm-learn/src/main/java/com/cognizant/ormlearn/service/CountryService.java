package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CountryService - Service layer for all Country operations.
 *
 * @Service  - Marks this as a Spring-managed service bean.
 *             Spring registers it in the ApplicationContext automatically
 *             via component scanning (configured by @SpringBootApplication).
 *
 * @Transactional - Tells Spring to wrap each method in a database transaction.
 *   Spring creates a Hibernate session before the method runs and commits
 *   (or rolls back on exception) after it finishes. Without this, reading
 *   from a lazy-loaded relationship outside a session would throw
 *   LazyInitializationException.
 *
 * @Autowired - Injects CountryRepository from Spring's container (DI).
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    // ----------------------------------------------------------------
    // Hands-on 1: getAllCountries (Quick Example)
    // ----------------------------------------------------------------

    /**
     * Returns all countries from the database.
     * JpaRepository.findAll() is provided automatically by Spring Data JPA.
     */
    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start getAllCountries");
        List<Country> countries = countryRepository.findAll();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End getAllCountries");
        return countries;
    }

    // ----------------------------------------------------------------
    // Hands-on 6: findCountryByCode
    // ----------------------------------------------------------------

    /**
     * Finds a country by its 2-letter ISO code.
     * Uses Optional to handle the "not found" case safely.
     *
     * @throws CountryNotFoundException if no country has the given code
     */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Start findCountryByCode: {}", countryCode);

        Optional<Country> result = countryRepository.findById(countryCode);

        if (!result.isPresent()) {
            throw new CountryNotFoundException(countryCode);
        }

        Country country = result.get();
        LOGGER.debug("Country found: {}", country);
        LOGGER.info("End findCountryByCode");
        return country;
    }

    // ----------------------------------------------------------------
    // Hands-on 7: addCountry
    // ----------------------------------------------------------------

    /**
     * Persists a new Country to the database.
     * JpaRepository.save() performs INSERT when the entity has no existing ID
     * and UPDATE when it does.
     */
    @Transactional
    public void addCountry(Country country) {
        LOGGER.info("Start addCountry: {}", country);
        countryRepository.save(country);
        LOGGER.info("End addCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 8: updateCountry
    // ----------------------------------------------------------------

    /**
     * Updates the name of an existing country identified by its code.
     */
    @Transactional
    public void updateCountry(String code, String newName) throws CountryNotFoundException {
        LOGGER.info("Start updateCountry: code={}, newName={}", code, newName);

        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException(code);
        }

        Country country = result.get();
        country.setName(newName);
        countryRepository.save(country); // Hibernate detects the change and issues UPDATE

        LOGGER.debug("Updated country: {}", country);
        LOGGER.info("End updateCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 9: deleteCountry
    // ----------------------------------------------------------------

    /**
     * Deletes the country with the given code from the database.
     */
    @Transactional
    public void deleteCountry(String code) {
        LOGGER.info("Start deleteCountry: {}", code);
        countryRepository.deleteById(code);
        LOGGER.info("End deleteCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 2 (spring-data-jpa-handson doc 2): Query Methods
    // ----------------------------------------------------------------

    /**
     * Finds countries whose name contains the search text (case-insensitive).
     */
    @Transactional
    public List<Country> findCountriesByNameContaining(String text) {
        LOGGER.info("Start findCountriesByNameContaining: {}", text);
        List<Country> result = countryRepository
                .findByNameContainingIgnoreCaseOrderByNameAsc(text);
        LOGGER.debug("Result: {}", result);
        LOGGER.info("End findCountriesByNameContaining");
        return result;
    }

    /**
     * Finds countries whose name starts with the given prefix.
     */
    @Transactional
    public List<Country> findCountriesByNameStartingWith(String prefix) {
        LOGGER.info("Start findCountriesByNameStartingWith: {}", prefix);
        List<Country> result = countryRepository.findByNameStartingWith(prefix);
        LOGGER.debug("Result: {}", result);
        LOGGER.info("End findCountriesByNameStartingWith");
        return result;
    }
}
