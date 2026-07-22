# Week 2 Tasks — Spring Core & Maven + Spring Data JPA

## Projects

```
week2-tasks/
├── LibraryManagement/          Spring Core (XML config) — Exercises 1, 2, 4
└── orm-learn/                  Spring Boot + Spring Data JPA — Hands-on 1, 2, 6-9
```

---

## Project 1: LibraryManagement (Spring Core + Maven)

**Covers:** Exercise 1 (Spring App setup), Exercise 2 (Dependency Injection), Exercise 4 (Maven config)

**IDE:** Open the `LibraryManagement/` folder as a Maven project in IntelliJ IDEA.

### Run

```bash
cd LibraryManagement
mvn compile exec:java
```

Or right-click `LibraryManagementApplication.java` → Run in IntelliJ.

### What to observe in output

- `Logger instance created.` appearing only **once** even though the class is wired to multiple beans → Spring IoC managing the lifecycle
- `[BookService] BookRepository injected via setter.` → confirms setter-based DI
- `[AOP - Before/After/Around]` lines around each `BookService` method call → AOP cross-cutting concerns working

### Key files

| File | Exercise |
|------|----------|
| `pom.xml` | Exercise 4: Maven dependencies + compiler plugin for Java 1.8 |
| `src/main/resources/applicationContext.xml` | Exercise 1: bean definitions; Exercise 2: `<property ref>` DI wiring |
| `com/library/repository/BookRepository.java` | Exercise 1: data access bean |
| `com/library/service/BookService.java` | Exercise 1 & 2: service bean with setter and constructor injection |
| `com/library/aspect/LoggingAspect.java` | Exercise 3 & 8: AOP logging (Before, After, Around) |
| `com/library/LibraryManagementApplication.java` | Exercise 1: main class loading the context |

---

## Project 2: orm-learn (Spring Boot + Spring Data JPA)

**Covers:** Hands-on 1 (Quick Example), Hands-on 4 (JPA vs Hibernate explanation), Hands-on 6-9 (CRUD), Hands-on 2 (Query Methods)

**IDE:** Open `orm-learn/` as a Maven project in IntelliJ IDEA.

### Run (uses H2 in-memory — no MySQL install needed)

```bash
cd orm-learn
mvn spring-boot:run
```

Or right-click `OrmLearnApplication.java` → Run in IntelliJ.

The app starts, seeds data, runs all test methods, and you'll see the results in the console.

### Switch to MySQL (optional)

1. Start MySQL 8 and create the schema:
   ```sql
   CREATE SCHEMA ormlearn;
   ```
2. In `application.properties`, comment out the H2 block and uncomment the MySQL block.
3. In `pom.xml`, comment out the H2 dependency and uncomment the MySQL connector.
4. Change `ddl-auto=create` to `ddl-auto=validate` after the first run.
5. Run the inserts from `data.sql` manually in MySQL Workbench.

### Key files

| File | Hands-on |
|------|----------|
| `pom.xml` | Boot + JPA + H2 dependencies |
| `src/main/resources/application.properties` | H2 (default) + commented MySQL config |
| `src/main/resources/data.sql` | Auto-loaded seed data for H2 |
| `model/Country.java` | @Entity mapping (Hands-on 1) |
| `repository/CountryRepository.java` | JpaRepository + Query Methods (Hands-on 1, 2) |
| `service/CountryService.java` | All CRUD + @Transactional (Hands-on 1, 6-9) |
| `OrmLearnApplication.java` | Test driver: all hands-on test methods |
| `JPA_vs_Hibernate_vs_SpringDataJPA.md` | Conceptual explanation (Hands-on 4) |

### Expected console output (abbreviated)

```
[Test] All Countries (14 total):
  AF  Afghanistan
  AU  Australia
  ...

[Test] findCountryByCode('IN') -> Country{code='IN', name='India'}
[Verification] Name is 'India': PASS ✓

[Test] addCountry -> Country{code='ZZ', name='Testland'}
[Verification] Added successfully: PASS ✓

[Test] updateCountry -> Country{code='ZZ', name='Updated Testland'}
[Verification] Updated successfully: PASS ✓

[Test] deleteCountry -> PASS ✓ (CountryNotFoundException thrown as expected)

[Test] Countries containing 'an' (sorted A-Z):
  DE  Germany
  JP  Japan
  ...

[Test] Countries starting with 'Z':
  ZM  Zambia
  ZW  Zimbabwe
```