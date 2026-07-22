package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * SpringLearnApplicationTests — MockMVC integration tests.
 *
 * @SpringBootTest loads the full application context.
 * @AutoConfigureMockMvc wires MockMvc without starting a real HTTP server.
 * @WithMockUser injects a fake Spring Security user, bypassing JWT/Basic auth.
 *
 * Tests mirror the handson doc 2 test scenarios:
 *   testContextLoads   → CountryController bean is in context
 *   testGetCountry     → /countries/india returns India with code=IN
 *   testGetAllCountries→ /countries returns array of 4 countries
 *   testGetByCode      → /countries/IN returns India
 *   testGetByCodeNotFound → /countries/xx returns 404
 *   testAddCountryValid   → POST /countries with valid JSON returns 200
 *   testAddCountryInvalid → POST /countries with invalid code returns 400
 */
@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(countryController, "CountryController should be wired by Spring");
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetCountryIndia() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/india"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllCountries() throws Exception {
        ResultActions actions = mvc.perform(get("/countries"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$.length()").value(4));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetCountryByCode() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/IN"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetCountryByCodeCaseInsensitive() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/in"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").value("IN"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetCountryNotFound() throws Exception {
        ResultActions actions = mvc.perform(get("/countries/xx"));
        actions.andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testAddCountryValid() throws Exception {
        String json = "{\"code\":\"AU\",\"name\":\"Australia\"}";
        ResultActions actions = mvc.perform(
                post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").value("AU"));
        actions.andExpect(jsonPath("$.name").value("Australia"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testAddCountryInvalidCode() throws Exception {
        // Code "A" is only 1 character — @Size(min=2,max=2) should reject it
        String json = "{\"code\":\"A\",\"name\":\"Australia\"}";
        ResultActions actions = mvc.perform(
                post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));
        actions.andExpect(status().isBadRequest());
        actions.andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testAddCountryNullName() throws Exception {
        String json = "{\"code\":\"AU\"}";
        ResultActions actions = mvc.perform(
                post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));
        actions.andExpect(status().isBadRequest());
    }
}
