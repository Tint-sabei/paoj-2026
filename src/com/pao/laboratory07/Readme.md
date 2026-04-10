Here is the English translation of your laboratory document.

# Laboratory 07 — State Machines, Advanced Enums, and Order Systems in eCommerce

> **Package:** `com.pao.laboratory07` · **Course:** 06 ·  
> **Deadline:** Wednesday, April 8, 2026, at 23:59

-----

## Theoretical Concepts: State Machines, Enums, and Command Pattern

### State Machine

A state machine is a computational model that describes a system through a finite set of states, transitions between those states, and possible actions. In the context of an online store, an order passes through states such as placed, processed, shipped, delivered, or canceled. Each action (e.g., "next", "cancel", "undo") triggers a transition between states.

### Advanced Enums in Java

An `enum` can elegantly model the states and transitions of a state machine. Each constant in the enum can have its own implementation for abstract methods, allowing for different behaviors for each state. Enums can have fields, private constructors, and methods, making them useful for associating specific logic with each state.

### Command Pattern

The Command Pattern decouples the object that issues a command from the one that executes it. In order tracking systems, commands (e.g., "next", "cancel", "undo") can be modeled as distinct objects or actions, facilitating extension and the management of action history (e.g., implementing undo functionality).

-----

\<details open\>
\<summary\>\<h2\>Objectives\</h2\>\</summary\>

1.  **State Machines and Advanced Enums** — modeling the lifecycle of an order.
2.  **Command Pattern and Action History** — implementing commands and undo/redo functionality.
3.  **Sealed Classes and Marker Interfaces** — safe and extensible type hierarchies.
4.  **Default/Private Methods in Interfaces** — reuse and extension of functionality.

\</details\>

-----

## Exercises

| \# | Package | Main Concept | Estimated Time | Automated Tests |
|---|--------|-------------------|--------------|----------------|
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | Enum with abstract methods, state machine, undo, eCommerce order tracking system | \~35 min | ✓ (3 parts) |
| 2 | [`exercise2/`](https://www.google.com/search?q=exercise2/Readme.md) | Sealed class hierarchy, composition, eCommerce order system extension | \~20 min | ✓ (flat) |
| 3 | [`exercise3/`](https://www.google.com/search?q=exercise3/Readme.md) | Advanced analysis: reports, statistics, and automated workflows for orders | \~45 min | manual |
| 4 *(bonus)* | [`exercise4/Readme.md`](https://www.google.com/search?q=exercise4/Readme.md) | Enum singleton, Java 9 validator chain | \~30 min | manual |

> **Total estimated:** \~1h 40 min (without bonus) · \~2h 10 min (with bonus)

-----

## How to Run Automated Tests

Open `exercise1/Test.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):  
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

- For **exercise1**, tests are organized by parts (`partA`, `partB`, `partC`).
- For **exercise2**, tests are `.in`/`.out` files located directly in `tests/` (no partA/partB/partC subdirectories).

-----

## Files in This Laboratory

| File | Role |
|--------------------------------------------|-------------------------------|
| [exercise1/Readme.md](https://www.google.com/search?q=exercise1/Readme.md) | Full requirements for Ex 1 |
| [exercise1/Main.java](https://www.google.com/search?q=exercise1/Main.java) | Entry point for Ex 1 |
| [exercise1/Test.java](https://www.google.com/search?q=exercise1/Test.java) | Automated test runner for Ex 1 |
| [exercise2/Readme.md](https://www.google.com/search?q=exercise2/Readme.md) | Full requirements for Ex 2 |
| [exercise2/Main.java](https://www.google.com/search?q=exercise2/Main.java) | Entry point for Ex 2 |
| [exercise2/Checker.java](https://www.google.com/search?q=exercise2/Checker.java) | Automated test runner for Ex 2 |
| [exercise3/Readme.md](https://www.google.com/search?q=exercise3/Readme.md) | Full requirements for Ex 3 |
| [exercise3/Main.java](https://www.google.com/search?q=exercise3/Main.java) | Entry point for Ex 3 |