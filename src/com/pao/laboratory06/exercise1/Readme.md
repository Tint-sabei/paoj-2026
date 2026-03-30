# Exercise 1 — Employee Management System

> **EXAMPLE:** This exercise is only for familiarization with the format and automated testing. The following exercises will have more advanced requirements and will be graded according to laboratory rules.
> **Package:** `com.pao.laboratory06.exercise1`
> **Estimated time:** ~35 min · **Automated tests:** yes (`Test.java`)

---

## Scenario

You are responsible for an internal application for a company. The application receives from
stdin a list of employees and a sorting criterion, and must display the sorted list.

In the future, new sorting criteria will appear — the architecture must be easy to extend
without modifying existing classes.

---

## General Requirement

Create in the package `com.pao.laboratory06.exercise1` all necessary classes and
implement `Main.java` that reads the data, sorts, and displays the result.

**There is no single correct way to structure the classes** — think about what each
class needs to know and do, and separate responsibilities.

---

## Input / Output Format

```
<N>
<name> <salary>      (N lines)
<criterion>
```

Possible criteria increase throughout the three parts (see below).

Output: one line per employee in the requested order, format `<name> <salary_double>`.

---

## Part A — Sort by salary (ascending)

Input criterion: `by_salary`

Implement natural sorting of employees by ascending salary.

**Example:**
```
Input:        Output:
3             Bob 3000.0
Alice 5000    Alice 5000.0
Bob 3000      Carol 7000.0
Carol 7000
by_salary
```

> 💡 Hint: there is a standard interface in Java that allows an object to compare itself
> with another object of the same type. `Arrays.sort` uses it automatically.

---

## Part B — Sort by name (alphabetical)

Input criterion: `by_name`

Add the ability to sort alphabetically by name, without modifying the `Angajat` class.

**Example:**
```
Input:        Output:
3             Alice 5000.0
Alice 5000    Bob 3000.0
Bob 3000      Carol 7000.0
Carol 7000
by_name
```

> 💡 Hint: there is a standard mechanism for defining alternative sorting criteria,
> separate from the class being sorted. `Arrays.sort` has a variant that accepts this object.

---

## Part C — Sort by salary descending

Input criterion: `by_salary_desc`

Add a third criterion. In case of equal salary, the original order must be preserved.

**Example:**
```
Input:          Output:
4               Carol 7000.0
Alice 5000      Alice 5000.0
Bob 3000        Bob 3000.0
Carol 7000      Dave 3000.0
Dave 3000
by_salary_desc
```

> 💡 Hint: you can reuse already existing criteria — reversing a comparison
> can be expressed simply. Think also about how to handle ties.

---

## How to run tests

Open `Test.java` and press **Run** in IntelliJ.
The working directory must be the project root (`paoj-2026/`).
You will see a block `Partea: partA / partB / partC` with `[PASS]` / `[FAIL]` per test.