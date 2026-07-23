package com.cognizant.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * LogFilter — GlobalFilter that logs every request passing through the gateway.
 *
 * Hands-on (API Gateway — global filter to log all requests):
 *   GlobalFilter applies to ALL routes (unlike GatewayFilter which applies to one).
 *   Returns Mono<Void> (reactive) not void (servlet) because Gateway uses WebFlux.
 *
 * After calling http://localhost:9090/account-service/accounts/123 you will see:
 *   ====>Request URL http://localhost:9090/account-service/accounts/123
 */
@Component
public class LogFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("====>Request URL {}", exchange.getRequest().getURI());
        return chain.filter(exchange);
    }
}
