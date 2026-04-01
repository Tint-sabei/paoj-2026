# Exercise 2 — Collaborator Hierarchy and Interfaces

> **Package:** `com.pao.laboratory06.exercise2`
> **Estimated time:** ~35 min · **Automated tests:** yes (`Test.java`)

---

## Scenario

We extend the Employee hierarchy to cover multiple types of collaborators: employees with employment contracts (CIM), 
sole proprietors (PFA), and limited liability companies (SRL). Each type has different rules for calculating annual 
net income. You will use interfaces, abstract classes, enums, and implement sorting, filtering, 
and summarization based on this data.

---

## Input Structure

Each input line starts with a keyword that determines the collaborator type:
- **CIM**: `CIM LastName FirstName GrossMonthlySalary [BONUS]` (BONUS = YES/NO, optional, default NO)
- **PFA**: `PFA LastName FirstName MonthlyIncome MonthlyExpenses`
- **SRL**: `SRL LastName FirstName MonthlyIncome MonthlyExpenses`

All amounts are monthly. For annual calculations, multiply by 12.

---

## General Requirements

1. Create an abstract class `Colaborator` with common fields (last name, first name, gross monthly income) and an abstract method `double calculateAnnualNetIncome()`.
2. Create subclasses for each type: `CIMColaborator`, `PFAColaborator`, `SRLColaborator`.
3. Create an interface `IOperationsReadWrite` with the following methods:
    - `void read(Scanner in)` — reads the object's data from input
    - `void display()` — displays the object's data in the required format
    - `String contractType()` — returns the contract type
    - `default boolean hasBonus() { return false; }` — only for CIM, if it has a bonus, the annual net income increases by 10%
4. Use an Enum `CollaboratorType` for the collaborator type (CIM, PFA, SRL).
5. The hierarchy must distinguish between individuals (`Individual`: CIM, PFA) and legal entities (`LegalEntity`: SRL).

---

## Annual Net Income Calculation Rules

- **CIM**: annual net income = gross monthly salary × 12 × 0.55; if there is a bonus, add 10% to the result.
- **PFA**: see details below (all amounts are annual):
    1. net income = (monthly income - monthly expenses) × 12
    2. income tax: 10% × net income
    3. CASS (health insurance, 10%):
        - if net income < 6 minimum gross salaries/year: 10% × (6 × minimum gross salary)
        - if net income between 6 and 72 minimum gross salaries/year: 10% × net income
        - if net income > 72 minimum gross salaries/year: 10% × (72 × minimum gross salary)
    4. CAS (pension, 25%):
        - if net income < 12 minimum gross salaries/year: not paid
        - if net income between 12 and 24 minimum gross salaries/year: 25% × (12 × minimum gross salary)
        - if net income > 24 minimum gross salaries/year: 25% × (24 × minimum gross salary)
    5. annual net income = net income - tax - CASS - CAS
  > For calculations, use the minimum gross salary valid in 2026 (4050 lei/month × 12 months = 48,600 lei/year).
- **SRL**: annual net income = (monthly income - monthly expenses) × 12 × 0.84 (only 16% profit tax applies).

---

## Additional Requirements

1. Sort collaborators in descending order by annual net income.
2. Display the collaborator with the maximum annual net income.
3. Display only legal entity collaborators (SRL).
4. For each collaborator type (CIM, PFA, SRL), display the sum of annual net incomes and the number of collaborators of that type.
5. Use polymorphism and enums to implement the above functionalities.

---

## Input Example
```
6
CIM Popescu Ana 8000 YES
CIM Ionescu Vlad 7000 NO
PFA Georgescu Maria 12000 2000
PFA Enache Paul 4000 500
SRL SRLTech SRL 20000 8000
SRL MicroSRL SRL 5000 2000
```

## Output Example
```
CIM: Popescu Ana, annual net income: 58080.00 RON
CIM: Ionescu Vlad, annual net income: 46200.00 RON
PFA: Georgescu Maria, annual net income: 86400.00 RON
PFA: Enache Paul, annual net income: 19200.00 RON
SRL: SRLTech SRL, annual net income: 115200.00 RON
SRL: MicroSRL SRL, annual net income: 30240.00 RON

Collaborator with maximum net income: SRL: SRLTech SRL, annual net income: 115200.00 RON

Legal entity collaborators:
SRL: SRLTech SRL, annual net income: 115200.00 RON
SRL: MicroSRL SRL, annual net income: 30240.00 RON

Sums and counts by collaborator type:
CIM: sum = 104280.00 RON, count = 2
PFA: sum = 105600.00 RON, count = 2
SRL: sum = 145440.00 RON, count = 2
```

> Test with other values to cover edge cases (PFA below/above thresholds, CIM with/without bonus, etc.).

---

## How to run tests

Open `Test.java` and press **Run** in IntelliJ.
The working directory must be the project root (`paoj-2026/`).