package com.cognizant.eurekaserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaDiscoveryServerApplication — service registry.
 * @EnableEurekaServer activates the Eureka dashboard and REST API.
 *
 * After startup open http://localhost:8761 to see:
 *   - Instances currently registered with Eureka
 *   - System status (uptime, memory, etc.)
 *
 * Startup order: always start Eureka FIRST, then account, loan, api-gateway.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
    }
}
