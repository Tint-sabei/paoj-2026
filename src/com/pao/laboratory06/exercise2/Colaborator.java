package com.pao.laboratory06.exercise2;

//1. Create an abstract class `Colaborator` with common fields (last name, first name, gross monthly income)
//and an abstract method `double calculateAnnualNetIncome()`.

//2. Create subclasses for each type: `CIMColaborator`, `PFAColaborator`, `SRLColaborator`.
//3. Create an interface `IOperationsReadWrite` with the following methods:
//        - `void read(Scanner in)` — reads the object's data from input
//        - `void display()` — displays the object's data in the required format
//        - `String contractType()` — returns the contract type
//    - `default boolean hasBonus() { return false; }` — only for CIM, if it has a bonus,
//the annual net income increases by 10%
//        4. Use an Enum `CollaboratorType` for the collaborator type (CIM, PFA, SRL).
//        5. The hierarchy must distinguish between individuals (`Individual`: CIM, PFA) and
//legal entities (`LegalEntity`: SRL).


public class Colaborator {
}
