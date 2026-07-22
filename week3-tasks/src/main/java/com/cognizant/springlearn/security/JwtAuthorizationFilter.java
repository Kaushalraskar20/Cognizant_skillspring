package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JwtAuthorizationFilter — validates JWT Bearer tokens on every request.
 *
 * JWT Hands-on (Authorize based on JWT):
 *
 * Flow per request:
 *   1. Check if Authorization header starts with "Bearer ".
 *   2. If yes, extract and validate the JWT using the shared secret key.
 *   3. If valid, set the parsed username as the authenticated principal
 *      in Spring Security's SecurityContextHolder.
 *   4. Request proceeds to the controller.
 *
 * SECRET_KEY must match the key used in AuthenticationController.generateJwt().
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    static final String SECRET_KEY   = "secretkey";
    static final String BEARER_PREFIX = "Bearer ";

    public JwtAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
        LOGGER.info("JwtAuthorizationFilter initialised");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Start doFilterInternal");

        String header = req.getHeader("Authorization");
        LOGGER.debug("Authorization header: {}", header);

        // No Bearer token → fall through to standard Spring Security handling
        // (e.g. HTTP Basic for /authenticate endpoint)
        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken auth = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(req, res);

        LOGGER.info("End doFilterInternal");
    }

    /** Parses the JWT and returns a Spring authentication token if valid. */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) return null;

        try {
            String token = header.replace(BEARER_PREFIX, "");
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            String user = jws.getBody().getSubject();
            LOGGER.debug("JWT valid, user={}", user);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(
                        user, null, new ArrayList<>());
            }
        } catch (JwtException ex) {
            LOGGER.warn("Invalid JWT: {}", ex.getMessage());
        }
        return null;
    }
}
