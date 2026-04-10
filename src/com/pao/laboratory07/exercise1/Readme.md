# Exercise 1 — Simple order tracking system for eCommerce

> **Package:** `com.pao.laboratory07.exercise1`
> **Estimated time:** ~35 min · **Automated tests:** yes (`Test.java`)

---

## Purpose

This exercise familiarizes you with the lab structure and automated testing. You will implement a simple order tracking system for an online store, using a single `Main.java` file and an enum for states.

---

## General Requirement

Create in the package `com.pao.laboratory07.exercise1`:
- an enum `OrderState` with the possible states of an order;
- a `Main.java` that reads orders from the keyboard, processes transitions, and displays results according to the requirements.

---

## Order States

An order can be in one of the following states:
- `PLACED` (the order has been placed)
- `PROCESSED` (the order is processed)
- `SHIPPED` (the order has been shipped)
- `DELIVERED` (the order has been delivered — final state)
- `CANCELED` (the order has been canceled — final state)

---

## Input and Output

- First line: initial state (`PLACED`, `PROCESSED`, `SHIPPED`, `DELIVERED`, `CANCELED`)
- Following lines: commands (`next`, `cancel`, `undo`) until `QUIT`
- For each command, display the new state or a special message if the transition cannot be made

---

## Part A — Basic Transitions

- Accepted commands: `next`, `QUIT`
- Transitions:
  - `PLACED` → `PROCESSED` → `SHIPPED` → `DELIVERED` (final state)
- At each step, display the current state

**Examples:**
```
Input:           Output:
PLACED           PLACED
next             PROCESSED
next             SHIPPED
next             DELIVERED
QUIT             User quit the program.
```

---

## Part B — Cancel and Final States

- Additional command: `cancel`
- If you receive `cancel` from any non-final state, transition to `CANCELED`
- If you are in a final state (`DELIVERED` or `CANCELED`), any command (`next`, `cancel`) displays: `Order is in final state.`
- Commands continue to be read until `QUIT`, but are ignored

- TODO if the order is in final state, any command (next, cancel) displays: Order is in final state.
  - The program continues to read commands until QUIT, but any command is ignored (only displays the final state message)

### Programming hints:
- Use a custom exception for canceling an order in final state

### **Example:**
```
Input:           Output:
DELIVERED          DELIVERED
next             Order is in final state.
cancel           Order is in final state.
QUIT
```

---

## Part C — Undo (return to previous state)

- Additional command: `undo`
- `undo` returns to the previous state (if there is one in history, otherwise stays in current state)
- If there is no previous state in history, display the message: `No previous state for undo.`
- `undo` also works if the current state is final (exit the final state!)
- On `undo`, do not display the final state message, only the new state or the message above

**Example:**
```
Input:           Output:
PLACED          PLACED
next             PROCESSED
next             SHIPPED
undo             PROCESSED
undo             PLACED
undo             No previous state for undo.
QUIT
```

**Example with final state and undo:**
```
Input:           Output:
DELIVERED        DELIVERED
undo             No previous state for undo.
QUIT
```

---

## Hints
- Use an enum for states and simple methods for transitions
- For `undo`, you can use a list or a stack to keep the state history

---

## How to run the tests

Open `Test.java` and press **Run** in IntelliJ.
The working directory must be the project root (`paoj-2026/`).