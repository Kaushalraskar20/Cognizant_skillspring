package com.cognizant.springlearn.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthenticationController — Step 1 of the JWT flow.
 *
 * JWT process:
 *   1. Client → GET /authenticate  (Authorization: Basic Base64(user:pwd))
 *   2. Spring Security validates the Basic credentials.
 *   3. This controller decodes the username, builds a signed JWT, returns it.
 *   4. Client attaches "Authorization: Bearer <token>" on subsequent calls.
 *   5. JwtAuthorizationFilter validates the token on each request.
 *
 * curl example:
 *   curl -s -u user:pwd http://localhost:8083/authenticate
 *   → {"token":"eyJhbGciOiJIUzI1NiJ9..."}
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthenticationController.class);

    private static final String SECRET_KEY = "secretkey";   // must match JwtAuthorizationFilter
    private static final long   EXPIRY_MS  = 1_200_000L;   // 20 minutes

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestHeader("Authorization") String authHeader) {

        LOGGER.info("Start authenticate");
        LOGGER.debug("authHeader={}", authHeader);

        String user  = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        LOGGER.info("End authenticate");
        return response;
    }

    /**
     * Decodes the HTTP Basic header and returns just the username.
     * Header format: "Basic <Base64(username:password)>"
     */
    private String getUser(String authHeader) {
        LOGGER.info("Start getUser");
        // Remove "Basic " prefix then Base64-decode
        String encoded  = authHeader.substring("Basic ".length());
        String decoded  = new String(Base64.getDecoder().decode(encoded));
        // "user:pwd" → take the part before ":"
        String user = decoded.substring(0, decoded.indexOf(":"));
        LOGGER.debug("Extracted user={}", user);
        LOGGER.info("End getUser");
        return user;
    }

    /**
     * Builds and signs a JWT.
     *
     * Token structure:
     *   Header  : {"alg":"HS256"}
     *   Payload : {"sub":"user", "iat":<now>, "exp":<now+20min>}
     *   Signature: HMAC-SHA256(header.payload, "secretkey")
     */
    private String generateJwt(String user) {
        LOGGER.info("Start generateJwt, user={}", user);
        String token = Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        LOGGER.info("End generateJwt");
        return token;
    }
}
