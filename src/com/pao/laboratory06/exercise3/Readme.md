# Exercise 3 — Online Payment Platform (CAN_DO, interfaces, sorting, SMS)

> **BONUS EXERCISE:** This exercise is optional and allows you to earn a 0.5% bonus on the final laboratory grade, if you participate in at least 8 exercises. You can achieve the maximum grade even if it doesn't round up to 50% in the final calculation. The exercise has no automated testing, but is recommended for deepening knowledge and to obtain the bonus.

> **Package:** `com.pao.laboratory06.exercise3`
> **Estimated time:** ~45 min · **Automated tests:** no (demonstration in `Main.java`)

---

## Scenario

You are modeling an online payment platform for a bank, with support for multiple types of users (individuals and legal entities) and different capabilities. Some users can only pay via bank account, while others can also receive SMS confirmation. For each client that has SMS capability, the messages sent will be stored and mapped to that client. Cases where a client does not have a phone number or attempts to send an SMS to an entity without this capability must also be handled.

---

## General Structure and Explicit Requirements

### 1. Interfaces
- `OnlinePayment` (basic interface):
  - `void authenticate(String user, String password)`
  - `double checkBalance()`
  - `boolean makePayment(double amount)`
- `OnlinePaymentSMS` (extends `OnlinePayment`):
  - `boolean sendSMS(String message)`
    - Returns `false` if the client does not have a valid phone number or if the message is null/empty.
    - If the method is called on an entity without SMS capability, throws `UnsupportedOperationException`.
- `FinancialConstants` (enum):
  - Enum with values such as `VAT`, `MINIMUM_WAGE`, `TAX_RATE` (e.g., `VAT(0.19)`, etc.).
  - Each constant has a getter for its value.

### 2. Classes and Hierarchy
- `Person` (abstract):
  - Fields: `String lastName`, `String firstName`, `String phone` (can be null or empty)
- `Employee` (extends `Person`):
  - Field: `double salary`
- `Engineer` (extends `Employee`, implements `OnlinePayment`, `Comparable<Engineer>`):
  - Natural order: by `lastName` (alphabetical)
  - All methods from `OnlinePayment` must be implemented
                                                                                                                                                                                                                 - Field: `List<String> sentSMS` (initialized empty)
  - Implementation of `sendSMS`: if `phone` is null/empty, returns `false` and does not add the message; otherwise, adds the message to the list and returns `true`.
- `EngineerSalaryComparator` (Comparator<Engineer>):
  - Sorts engineers in descending order by salary.

### 3. Edge Cases and Validation
- For any method that receives null/empty arguments, throw `IllegalArgumentException` or return `false` (specified per method).
- For `sendSMS`, if called on an entity without SMS capability, throw `UnsupportedOperationException`.
- For `authenticate`, if user or password are null/empty, throw `IllegalArgumentException`.

### 4. Mandatory Demonstrations in Main.java
- Create and sort an array of `Engineer` (natural order and with the salary comparator).
- Demonstrate access to an `Engineer` only through an `OnlinePayment` reference (you cannot access engineer-specific methods).
- Demonstrate access to a `LegalEntity` through an `OnlinePaymentSMS` reference and storing sent messages, including cases without a phone number or with invalid messages.
- Display at least one constant from the financial constants enum.
- Demonstrate error case handling (e.g., sending SMS without phone, calling on wrong entity, authentication with null user).

---

## Hints and Recommendations
- Use `extends` between interfaces to model additional capabilities (e.g., SMS).
- A class can implement multiple interfaces simultaneously.
- An interface type reference allows access only to the methods of that interface, regardless of the object's concrete type.
- For storing SMS messages, use an `ArrayList<String>` in each `LegalEntity`.
- For alternative sorting, implement a separate `Comparator`.
- For constants, use an enum with a getter.
- Comment the code to explain design decisions and error handling.

---

## Recommended Package Structure
- `Person` (abstract)
- `Employee` (extends Person)
- `Engineer` (extends Employee, implements OnlinePayment and Comparable)
- `LegalEntity` (extends Person, implements OnlinePaymentSMS)
- `EngineerSalaryComparator` (alternative sorting criterion)
- `OnlinePayment` (base interface)
- `OnlinePaymentSMS` (sub-interface with SMS)
- `FinancialConstants` (enum for constants)

---

> No fixed input/output is required. Demonstrate all required functionalities in `Main.java` with relevant examples and comments for each case, including edge cases.