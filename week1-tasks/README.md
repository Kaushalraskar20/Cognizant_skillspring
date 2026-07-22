# Week 1 Tasks

Folder layout:

```
week1-tasks/
├── design-patterns/
│   ├── SingletonPatternExample/src/      (Exercise 1)
│   └── FactoryMethodPatternExample/src/  (Exercise 2)
├── dsa/
│   ├── Exercise2-EcommerceSearch/src/
│   └── Exercise7-FinancialForecasting/src/
└── plsql/
    ├── schema.sql                   (run this first)
    ├── Exercise1_ControlStructures.sql
    └── Exercise3_StoredProcedures.sql
```

## Running the Java exercises

Each `src/` folder is self-contained. From inside it:

```bash
javac *.java
java LoggerTest              # Singleton
java FactoryMethodTest        # Factory Method
java SearchDemo                # E-commerce search
java FinancialForecast        # Financial forecasting
```


## Running the PL/SQL exercises

1. Open **Oracle Live SQL** (apex.oracle.com/livesql) or your local Oracle/DBeaver setup.
2. Run `schema.sql` first — creates the tables and inserts sample data.
3. Run `Exercise1_ControlStructures.sql` — three anonymous PL/SQL blocks (Scenarios 1–3).
4. Run `Exercise3_StoredProcedures.sql` — creates the three procedures, then calls each one as a test.
5. To see `DBMS_OUTPUT.PUT_LINE` output in Live SQL, the output panel shows it automatically. In SQL*Plus/SQL Developer, run `SET SERVEROUTPUT ON` first.

**Note:** the original schema only included `Loans.InterestRate` (per loan, not per customer), so Exercise 1 Scenario 1 discounts the interest rate on each loan belonging to customers over 60 — not a customer-level field. I also added an `IsVIP CHAR(1)` column to `Customers` since the exercise needs a flag to set, which wasn't in the original handout schema.
