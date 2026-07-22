# Week 3 — Spring REST using Spring Boot

### Project: `spring-learn` (port 8083)

| Hands-on doc | Exercise | File |
|---|---|---|
| 1. spring-rest-handson | Hands-on 1: Maven project | `pom.xml` |
| 1. spring-rest-handson | Hands-on 2: Date format XML bean | `date-format.xml`, `displayDate()` |
| 1. spring-rest-handson | Hands-on 4: Country XML bean | `country.xml`, `displayCountry()` |
| 1. spring-rest-handson | Hands-on 6: Country list XML bean | `country.xml`, `displayCountries()` |
| 2. spring-rest-handson | Hello World REST | `GET /hello` |
| 2. spring-rest-handson | Country Web Service | `GET /countries/india` |
| 2. spring-rest-handson | Get all countries | `GET /countries` |
| 2. spring-rest-handson | Get by code + 404 | `GET /countries/{code}` |
| 2. spring-rest-handson | MockMVC tests | `SpringLearnApplicationTests.java` |
| 4. spring-rest-handson | POST + @Valid | `POST /countries` |
| 4. spring-rest-handson | PUT / DELETE | `PUT/DELETE /countries/{code}` |
| 4. spring-rest-handson | GlobalExceptionHandler | `GlobalExceptionHandler.java` |
| 5. JWT-handson | Spring Security + users | `SecurityConfig.java` |
| 5. JWT-handson | GET /authenticate → JWT | `AuthenticationController.java` |
| 5. JWT-handson | Bearer token filter | `JwtAuthorizationFilter.java` |

**Run:**
```bash
cd spring-learn && mvn spring-boot:run
```

**JWT test flow (use Postman or curl):**
```bash
# Step 1: get token (HTTP Basic → JWT)
curl -s -u user:pwd http://localhost:8083/authenticate
# {"token":"eyJhbGci..."}

# Step 2: use token on all subsequent calls
curl -s -H "Authorization: Bearer <TOKEN>" http://localhost:8083/hello
curl -s -H "Authorization: Bearer <TOKEN>" http://localhost:8083/countries
curl -s -H "Authorization: Bearer <TOKEN>" http://localhost:8083/countries/IN
curl -s -H "Authorization: Bearer <TOKEN>" http://localhost:8083/countries/xx   # → 404

# Step 3: POST with validation
curl -s -H "Authorization: Bearer <TOKEN>" \
     -H "Content-Type: application/json" \
     -X POST -d '{"code":"AU","name":"Australia"}' \
     http://localhost:8083/countries

# Invalid (code too short → 400):
curl -s -H "Authorization: Bearer <TOKEN>" \
     -H "Content-Type: application/json" \
     -X POST -d '{"code":"A","name":"Australia"}' \
     http://localhost:8083/countries
```

**Run MockMVC tests:**
```bash
cd spring-learn && mvn test
```