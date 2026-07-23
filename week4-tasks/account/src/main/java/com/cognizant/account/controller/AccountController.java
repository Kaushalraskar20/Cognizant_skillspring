package com.cognizant.account.controller;
import com.cognizant.account.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * AccountController — REST controller for account microservice.
 * Hands-on: GET /accounts/{number} returns dummy account (no DB).
 * Test direct:      http://localhost:8080/accounts/00987987973432
 * Test via gateway: http://localhost:9090/account-service/accounts/00987987973432
 */
@RestController
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable String number) {
        LOGGER.info("Start getAccount, number={}", number);
        Account account = new Account(number, "savings", 234343);
        LOGGER.debug("account type={}, balance={}", account.getType(), account.getBalance());
        LOGGER.info("End getAccount");
        return account;
    }
}
