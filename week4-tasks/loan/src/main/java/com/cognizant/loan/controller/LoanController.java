package com.cognizant.loan.controller;
import com.cognizant.loan.model.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * LoanController — REST controller for loan microservice.
 * Hands-on: GET /loans/{number} returns dummy loan (no DB).
 * Test direct:      http://localhost:8081/loans/H00987987972342
 * Test via gateway: http://localhost:9090/loan-service/loans/H00987987972342
 */
@RestController
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    @GetMapping("/loans/{number}")
    public Loan getLoan(@PathVariable String number) {
        LOGGER.info("Start getLoan, number={}", number);
        Loan loan = new Loan(number, "car", 400000, 3258, 18);
        LOGGER.debug("loan type={}, amount={}", loan.getType(), loan.getLoan());
        LOGGER.info("End getLoan");
        return loan;
    }
}
