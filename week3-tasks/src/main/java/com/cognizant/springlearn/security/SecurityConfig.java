package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig — Spring Security configuration for spring-learn.
 *
 * JWT Hands-on steps implemented here:
 *   Step 1: @EnableWebSecurity — all URLs require authentication.
 *   Step 2: Two in-memory users: user/pwd (USER), admin/pwd (ADMIN).
 *   Step 3: Role-based URL rules + HTTP Basic for /authenticate.
 *   Step 4: JwtAuthorizationFilter plugged into the filter chain.
 *
 * Access rules:
 *   /authenticate  → USER or ADMIN (exchanges Basic creds for JWT)
 *   /countries     → USER only
 *   Everything else → authenticated (any valid JWT)
 *
 * Test sequence:
 *   1. curl -s -u user:pwd  http://localhost:8083/authenticate  → {"token":"..."}
 *   2. curl -s -H "Authorization: Bearer TOKEN" http://localhost:8083/countries
 *   3. curl -s -u admin:pwd http://localhost:8083/countries  → 403 (wrong role for /countries)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("Configuring in-memory users");
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info("Configuring HttpSecurity");
        http
            .csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
                .antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                .antMatchers("/countries/**").hasRole("USER")
                .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthorizationFilter(authenticationManager()));
    }
}
