# Week 4 — Microservices with Spring Boot and Spring Cloud

### Projects (all inside `microservices/`)

| Project | Port | Role |
|---|---|---|
| `eureka-discovery-server` | 8761 | Service registry |
| `account` | 8080 | Account microservice |
| `loan` | 8081 | Loan microservice |
| `api-gateway` | 9090 | API Gateway + LogFilter |

### Startup order (ALWAYS start Eureka first)

```bash
# Terminal 1
cd microservices/eureka-discovery-server && mvn spring-boot:run
# Open http://localhost:8761 → empty registry

# Terminal 2
cd microservices/account && mvn spring-boot:run
# Refresh http://localhost:8761 → ACCOUNT-SERVICE appears

# Terminal 3
cd microservices/loan && mvn spring-boot:run
# Refresh http://localhost:8761 → LOAN-SERVICE appears

# Terminal 4
cd microservices/api-gateway && mvn spring-boot:run
# Refresh http://localhost:8761 → API-GATEWAY appears
```

### Test URLs

**Direct (bypassing gateway):**
```
GET http://localhost:8080/accounts/00987987973432
GET http://localhost:8081/loans/H00987987972342
```

**Via API Gateway:**
```
GET http://localhost:9090/account-service/accounts/00987987973432
GET http://localhost:9090/loan-service/loans/H00987987972342
```

**Expected responses:**
```json
// Account
{"number":"00987987973432","type":"savings","balance":234343.0}

// Loan
{"number":"H00987987972342","type":"car","loan":400000.0,"emi":3258.0,"tenure":18}
```

**Verify LogFilter in api-gateway console:**
```
====>Request URL http://localhost:9090/account-service/accounts/00987987973432
```

