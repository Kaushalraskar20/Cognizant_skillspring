-- ============================================================
-- Exercise 1: Control Structures
-- Run schema.sql first.
-- ============================================================

-- ------------------------------------------------------------
-- Scenario 1: Apply a 1% discount to loan interest rates for
-- customers above 60 years old.
-- ------------------------------------------------------------
DECLARE
    v_age NUMBER;
BEGIN
    FOR cust IN (SELECT CustomerID, DOB FROM Customers) LOOP

        -- Calculate age in whole years from DOB to today.
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - (InterestRate * 0.01)
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ' || cust.CustomerID ||
                ' (age ' || v_age || ') -> 1% loan discount applied.');
        END IF;

    END LOOP;
    COMMIT;
END;
/


-- ------------------------------------------------------------
-- Scenario 2: Set IsVIP = 'Y' for customers with balance > 10000.
-- ------------------------------------------------------------
BEGIN
    FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP

        IF cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'Y'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ' || cust.CustomerID ||
                ' (balance ' || cust.Balance || ') -> marked as VIP.');
        ELSE
            UPDATE Customers
            SET IsVIP = 'N'
            WHERE CustomerID = cust.CustomerID;
        END IF;

    END LOOP;
    COMMIT;
END;
/


-- ------------------------------------------------------------
-- Scenario 3: Print a reminder for every loan due within the
-- next 30 days.
-- ------------------------------------------------------------
BEGIN
    FOR rec IN (
        SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan #' || rec.LoanID ||
            ' for customer ' || rec.Name ||
            ' is due on ' || TO_CHAR(rec.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
