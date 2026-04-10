# Exercise 2 — Sealed command hierarchy

> **Package:** `com.pao.laboratory07.exercise2`
> **Estimated time:** ~20 min · **Automated tests:** yes (`Checker.java`)

---

## Purpose

You will model command types using **sealed classes** and reuse the `OrderState` enum from Exercise 1 to mark the initial state of each command.

---

## Sealed hierarchy

Create a sealed class `Comanda` that allows exactly three subclasses:

```java
public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String name;
    // ...
    public abstract double finalPrice();
    public abstract String description();
}
```

| Subclass | Extra fields | `finalPrice()` |
|---|---|---|
| `ComandaStandard` | — | `price` |
| `ComandaRedusa` | `int discountPercent` | `price * (1 - discountPercent / 100.0)` |
| `ComandaGratuita` | — | `0.0` |

---

## Import from Exercise 1

Import the state enum from the previous exercise:

```java
import com.pao.laboratory07.exercise1.OrderState;
```

Each command automatically receives the initial state `OrderState.PLACED` upon construction. There's no need to read it from input — it's always `PLACED`.

---

## Input structure

- First line: number of commands `N`
- Next `N` lines, each in one of the formats:
  - `STANDARD <name> <price>`
  - `DISCOUNTED <name> <price> <discountPercent>`
  - `GIFT <name>`

---

## Output structure

**For each command**, on separate lines, in the order read:

```
STANDARD: <name>, price: X.XX lei [PLACED]
DISCOUNTED: <name>, price: X.XX lei (-D%) [PLACED]
GIFT: <name>, free [PLACED]
```

> The displayed price is the **final price** (after discount).

**One blank line**, then the statistics block:

```
Statistics:
STANDARD: sum = X.XX lei, count = N
DISCOUNTED: sum = X.XX lei, count = N
GIFT: sum = 0.00 lei, count = N
Total paid: X.XX lei
```

> Display only the types present in the input (do not display a statistics line for a type with 0 commands).  
> Order of types in statistics: `STANDARD`, `DISCOUNTED`, `GIFT` (if they exist).

---

## Complete example

**Input:**
```
4
STANDARD Laptop 2500.0
DISCOUNTED Headphones 200.0 20
GIFT Sticker
STANDARD Mouse 80.0
```

**Output:**
```
STANDARD: Laptop, price: 2500.00 lei [PLACED]
DISCOUNTED: Headphones, price: 160.00 lei (-20%) [PLACED]
GIFT: Sticker, free [PLACED]
STANDARD: Mouse, price: 80.00 lei [PLACED]

Statistics:
STANDARD: sum = 2580.00 lei, count = 2
DISCOUNTED: sum = 160.00 lei, count = 1
GIFT: sum = 0.00 lei, count = 1
Total paid: 2740.00 lei
```

---

## Instructions

- Use `pattern matching` with `instanceof` or a `switch` on the sealed type to generate the description.
- Collect statistics in a `Map<String, Double>` for sums and a `Map<String, Integer>` for counts, or simply with three variables per type.
- Format monetary values with `String.format("%.2f", ...)`.
- `sealed` and `permits` require Java 17+.

---

## Automated testing

Open `Checker.java` and press **Run** in IntelliJ. The tests are located directly in `tests/` (files `1.in`/`1.out` … `4.in`/`4.out`).  
The working directory must be the project root (`paoj-2026/`).