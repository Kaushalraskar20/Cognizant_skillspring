package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * CountryController — REST controller for all country endpoints.
 *
 * URL map:
 *   GET  /country           → India bean from country.xml       (doc 2, Country Web Service)
 *   GET  /countries         → all four countries from XML       (doc 2, Get All Countries)
 *   GET  /countries/{code}  → lookup by code, 404 if missing    (doc 2, Get by Code)
 *   POST /countries         → validate and echo new country     (doc 4, POST + @Valid)
 *   PUT  /countries/{code}  → update country name               (doc 4, PUT)
 *   DELETE /countries/{code}→ remove country                    (doc 4, DELETE)
 *
 * Class-level @RequestMapping("/countries") applies to all methods.
 * /country (singular) is a separate explicit path defined at method level —
 * Spring allows full-path overrides at method level.
 */
@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.debug("Inside CountryController constructor.");
    }

    // ----------------------------------------------------------------
    // Hands-on doc 2: GET /country — load India bean directly from XML
    // @RequestMapping with full path escapes the class-level /countries
    // ----------------------------------------------------------------
    @RequestMapping("/india")
    public Country getCountryIndia() {
        LOGGER.info("Start getCountryIndia");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("country={}", country);
        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End getCountryIndia");
        return country;
    }

    // ----------------------------------------------------------------
    // Hands-on doc 2: GET /countries — return the full list from XML
    // ----------------------------------------------------------------
    @GetMapping
    public List<Country> getAllCountries() {
        LOGGER.info("Start getAllCountries");
        List<Country> countries = countryService.getCountryList();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End getAllCountries");
        return countries;
    }

    // ----------------------------------------------------------------
    // Hands-on doc 2: GET /countries/{code} — case-insensitive lookup
    // Throws CountryNotFoundException → Spring returns HTTP 404
    // ----------------------------------------------------------------
    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start getCountry, code={}", code);
        Country country = countryService.getCountry(code);
        LOGGER.debug("country={}", country);
        LOGGER.info("End getCountry");
        return country;
    }

    // ----------------------------------------------------------------
    // Hands-on doc 4: POST /countries — validate and echo back country
    // @RequestBody: Spring/Jackson deserializes JSON → Country
    // @Valid: triggers @NotNull / @Size checks before this method runs
    //         Violations → GlobalExceptionHandler.handleMethodArgumentNotValid
    // ----------------------------------------------------------------
    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start addCountry");
        LOGGER.debug("Received: {}", country);
        // In production, persist via CountryRepository here
        LOGGER.info("End addCountry");
        return country;
    }

    // ----------------------------------------------------------------
    // Hands-on doc 4: PUT /countries/{code} — update country name
    // ----------------------------------------------------------------
    @PutMapping("/{code}")
    public Country updateCountry(@PathVariable String code,
                                  @RequestBody @Valid Country updated)
            throws CountryNotFoundException {
        LOGGER.info("Start updateCountry, code={}", code);
        // Verify the country exists before updating
        countryService.getCountry(code);
        updated.setCode(code);
        LOGGER.debug("Updated: {}", updated);
        LOGGER.info("End updateCountry");
        return updated;
    }

    // ----------------------------------------------------------------
    // Hands-on doc 4: DELETE /countries/{code}
    // ----------------------------------------------------------------
    @DeleteMapping("/{code}")
    public String deleteCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start deleteCountry, code={}", code);
        countryService.getCountry(code); // Throws 404 if not found
        LOGGER.info("End deleteCountry");
        return "Country " + code + " deleted successfully";
    }
}
