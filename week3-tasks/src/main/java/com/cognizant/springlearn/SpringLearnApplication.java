package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * SpringLearnApplication — main entry point.
 *
 * Hands-on 1 : @SpringBootApplication starts embedded Tomcat and
 *              loads all @RestController / @Service beans.
 * Hands-on 2 : displayDate() reads SimpleDateFormat from date-format.xml.
 * Hands-on 4 : displayCountry() loads a single Country bean from country.xml.
 * Hands-on 6 : displayCountries() loads the ArrayList of four countries.
 */
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Start main");
        SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication app = new SpringLearnApplication();
        app.displayDate();
        app.displayCountry();
        app.displayCountries();

        LOGGER.info("End main");
    }

    // ----------------------------------------------------------------
    // Hands-on 2: load SimpleDateFormat from Spring XML and parse a date
    // ----------------------------------------------------------------
    public void displayDate() {
        LOGGER.info("Start displayDate");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        try {
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed date: {}", date);
        } catch (ParseException e) {
            LOGGER.error("Date parsing failed: {}", e.getMessage());
        }
        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End displayDate");
    }

    // ----------------------------------------------------------------
    // Hands-on 4: load single Country bean from country.xml
    // ----------------------------------------------------------------
    public void displayCountry() {
        LOGGER.info("Start displayCountry");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country: {}", country);

        // Hands-on 5 — singleton proof: both references point to same object
        Country anotherCountry = context.getBean("country", Country.class);
        LOGGER.debug("Same instance? {}", (country == anotherCountry));

        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End displayCountry");
    }

    // ----------------------------------------------------------------
    // Hands-on 6: load ArrayList of countries from country.xml
    // ----------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("Start displayCountries");
        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", List.class);
        LOGGER.debug("Countries: {}", countries);
        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End displayCountries");
    }
}
