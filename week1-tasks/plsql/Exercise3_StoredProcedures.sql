-- ============================================================
-- Exercise 3: Stored Procedures
-- Run schema.sql first.
-- ============================================================

-- ------------------------------------------------------------
-- Scenario 1: ProcessMonthlyInterest
-- Applies 1% interest to all savings account balances.
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' savings account(s) updated with monthly interest.');
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in ProcessMonthlyInterest: ' || SQLERRM);
        ROLLBACK;
END ProcessMonthlyInterest;
/


-- ------------------------------------------------------------
-- Scenario 2: UpdateEmployeeBonus
-- Adds a bonus percentage to the salary of all employees in a
-- given department.
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department  IN VARCHAR2,
    p_bonusPct    IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_bonusPct / 100))
    WHERE Department = p_department;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' employee(s) in ' || p_department ||
            ' received a ' || p_bonusPct || '% bonus.');
    END IF;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in UpdateEmployeeBonus: ' || SQLERRM);
        ROLLBACK;
END UpdateEmployeeBonus;
/


-- ------------------------------------------------------------
-- Scenario 3: TransferFunds
-- Transfers a specified amount from one account to another,
-- checking that the source account has sufficient balance first.
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_fromAccountId IN NUMBER,
    p_toAccountId   IN NUMBER,
    p_amount        IN NUMBER
) AS
    v_fromBalance NUMBER;
BEGIN
    SELECT Balance INTO v_fromBalance
    FROM Accounts
    WHERE AccountID = p_fromAccountId
    FOR UPDATE;  -- lock the row while we check/update it

    IF v_fromBalance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Transfer failed: insufficient balance in account ' ||
            p_fromAccountId || '.');
        ROLLBACK;
        RETURN;
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_fromAccountId;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_toAccountId;

    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account ' ||
        p_fromAccountId || ' to account ' || p_toAccountId || '.');
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: one of the accounts does not exist.');
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in TransferFunds: ' || SQLERRM);
        ROLLBACK;
END TransferFunds;
/


-- ------------------------------------------------------------
-- Example calls to test the procedures above.
-- ------------------------------------------------------------
BEGIN
    ProcessMonthlyInterest();
END;
/

BEGIN
    UpdateEmployeeBonus('IT', 10);   -- 10% bonus for IT department
END;
/

BEGIN
    TransferFunds(3, 1, 500);        -- move 500 from account 3 to account 1
END;
/
