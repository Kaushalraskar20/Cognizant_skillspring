package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController — simplest possible REST endpoint.
 *
 * Hands-on doc 2 (Hello World RESTful Web Service):
 *   GET /hello → "Hello World!!"
 *
 * Test:
 *   Step 1: GET /authenticate with -u user:pwd  → get JWT token
 *   Step 2: GET /hello with "Authorization: Bearer <token>"
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("Start sayHello");
        String response = "Hello World!!";
        LOGGER.debug("response={}", response);
        LOGGER.info("End sayHello");
        return response;
    }
}
