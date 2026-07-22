package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * OrmLearnApplication - Spring Boot entry point.
 *
 * @SpringBootApplication is a convenience annotation that combines:
 *   - @Configuration     : this class can define Spring beans
 *   - @EnableAutoConfiguration : Spring Boot auto-configures beans based on classpath
 *   - @ComponentScan     : scans com.cognizant.ormlearn.* for @Service, @Repository, etc.
 *
 * Hands-on 1 Quick Example: demonstrates getAllCountries via Spring Data JPA.
 * Hands-on 6-9: demonstrates find, add, update, delete operations.
 * Hands-on 2 Query Methods: demonstrates Spring Data JPA query methods.
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // Static reference to CountryService, resolved from the Spring context after startup.
    private static CountryService countryService;

    public static void main(String[] args) {
        // SpringApplication.run() starts the embedded web server, loads all beans,
        // and returns the ApplicationContext.
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main - Spring context loaded");

        // Retrieve CountryService from the context.
        countryService = context.getBean(CountryService.class);

        // ------------------------------------------------------------
        // Comment/uncomment test methods to run each hands-on exercise.
        // ------------------------------------------------------------

        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testQueryMethods();
    }

    // ----------------------------------------------------------------
    // Hands-on 1: Get all countries
    // ----------------------------------------------------------------
    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        System.out.println("\n[Test] All Countries (" + countries.size() + " total):");
        countries.forEach(c -> System.out.println("  " + c.getCode() + "  " + c.getName()));
        LOGGER.info("End testGetAllCountries");
    }

    // ----------------------------------------------------------------
    // Hands-on 6: Find country by code
    // ----------------------------------------------------------------
    private static void testFindCountryByCode() {
        LOGGER.info("Start testFindCountryByCode");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
            System.out.println("\n[Test] findCountryByCode('IN') -> " + country);
            // Verify the name matches what we inserted
            System.out.println("[Verification] Name is 'India': "
                    + ("India".equals(country.getName()) ? "PASS ✓" : "FAIL ✗"));
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        LOGGER.info("End testFindCountryByCode");
    }

    // ----------------------------------------------------------------
    // Hands-on 7: Add a new country
    // ----------------------------------------------------------------
    private static void testAddCountry() {
        LOGGER.info("Start testAddCountry");
        Country newCountry = new Country("ZZ", "Testland");
        countryService.addCountry(newCountry);

        try {
            Country fetched = countryService.findCountryByCode("ZZ");
            System.out.println("\n[Test] addCountry -> " + fetched);
            System.out.println("[Verification] Added successfully: "
                    + ("Testland".equals(fetched.getName()) ? "PASS ✓" : "FAIL ✗"));
        } catch (CountryNotFoundException e) {
            System.out.println("[Test] addCountry FAILED - country not found after insert");
        }
        LOGGER.info("End testAddCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 8: Update a country
    // ----------------------------------------------------------------
    private static void testUpdateCountry() {
        LOGGER.info("Start testUpdateCountry");
        try {
            countryService.updateCountry("ZZ", "Updated Testland");
            Country updated = countryService.findCountryByCode("ZZ");
            System.out.println("\n[Test] updateCountry -> " + updated);
            System.out.println("[Verification] Updated successfully: "
                    + ("Updated Testland".equals(updated.getName()) ? "PASS ✓" : "FAIL ✗"));
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        LOGGER.info("End testUpdateCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 9: Delete a country
    // ----------------------------------------------------------------
    private static void testDeleteCountry() {
        LOGGER.info("Start testDeleteCountry");
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
            System.out.println("\n[Test] deleteCountry FAILED - country still found");
        } catch (CountryNotFoundException e) {
            System.out.println("\n[Test] deleteCountry -> PASS ✓ (CountryNotFoundException thrown as expected)");
        }
        LOGGER.info("End testDeleteCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 2 (doc 2): Spring Data JPA Query Methods
    // ----------------------------------------------------------------
    private static void testQueryMethods() {
        LOGGER.info("Start testQueryMethods");

        System.out.println("\n[Test] Countries containing 'an' (sorted A-Z):");
        List<Country> anCountries = countryService.findCountriesByNameContaining("an");
        anCountries.forEach(c -> System.out.println("  " + c.getCode() + "  " + c.getName()));

        System.out.println("\n[Test] Countries starting with 'Z':");
        List<Country> zCountries = countryService.findCountriesByNameStartingWith("Z");
        zCountries.forEach(c -> System.out.println("  " + c.getCode() + "  " + c.getName()));

        LOGGER.info("End testQueryMethods");
    }
}
