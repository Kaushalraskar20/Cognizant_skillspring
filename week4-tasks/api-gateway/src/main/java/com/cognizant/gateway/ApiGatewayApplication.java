package com.cognizant.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ApiGatewayApplication — Spring Cloud API Gateway entry point.
 * Runs on port 9090. Routes to downstream services via Eureka.
 *
 * Test URLs (all 4 services must be running):
 *   http://localhost:9090/account-service/accounts/00987987973432
 *   http://localhost:9090/loan-service/loans/H00987987972342
 *
 * Check api-gateway console for:
 *   ====>Request URL http://localhost:9090/account-service/...
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
