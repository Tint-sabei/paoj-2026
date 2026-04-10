# Exercise 3 (BONUS) — Analysis with Stream API for orders

> **Package:** `com.pao.laboratory07.exercise3`
> **Estimated time:** ~30 min · **Automated tests:** no (demonstration in `Main.java`)

---

## Purpose

Extend the `Comanda` classes from Exercise 2 (add the `client` field) and implement analysis and filtering operations using **Stream API**.

---

## Extension of Classes from Exercise 2

Reuse the sealed hierarchy from exercise 2. Add the `String client` field to the `Comanda` class:

```java
public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String name;
    protected String client;  // ← ADD THIS
    // ...
}
```

---

## Input structure

- First line: number of commands `N`
- Next `N` lines, each in the format from exercise 2, followed by the order's client:
  - `STANDARD <name> <price> <client>`
  - `DISCOUNTED <name> <price> <discountPercent> <client>`
  - `GIFT <name> <client>`
- Following lines: commands (`STATS`, `FILTER <threshold>`, `SORT`, `SPECIAL`, `QUIT`)

---

## Output structure

**For each command**, on separate lines (format from exercise 2):
```
STANDARD: <name>, price: X.XX lei [PLACED] - client: <client>
```

**Then, for each received command:**

- `STATS` — Display the averages of final prices grouped by type (STANDARD, DISCOUNTED, GIFT)
- `FILTER <threshold>` — Display orders with final price ≥ threshold
- `SORT` — Display orders sorted by client, then by final price (ascending)
- `SPECIAL` — Display orders with discount greater than 15% (if they are DISCOUNTED)
- `QUIT` — The program ends

---

## Complete example

**Input:**
```
5
STANDARD Laptop 2500.0 Alice
DISCOUNTED Headphones 200.0 20 Bob
GIFT Sticker Charlie
STANDARD Mouse 80.0 Alice
DISCOUNTED Keyboard 300.0 10 Dave
STATS
FILTER 100
SORT
SPECIAL
QUIT
```

**Output (example with flexibility):**
```
STANDARD: Laptop, price: 2500.00 lei [PLACED] - client: Alice
DISCOUNTED: Headphones, price: 160.00 lei (-20%) [PLACED] - client: Bob
GIFT: Sticker, free [PLACED] - client: Charlie
STANDARD: Mouse, price: 80.00 lei [PLACED] - client: Alice
DISCOUNTED: Keyboard, price: 270.00 lei (-10%) [PLACED] - client: Dave

--- STATS ---
STANDARD: average = 1290.00 lei
DISCOUNTED: average = 215.00 lei
GIFT: average = 0.00 lei

--- FILTER (>= 100.00) ---
STANDARD: Laptop, price: 2500.00 lei - client: Alice
DISCOUNTED: Headphones, price: 160.00 lei - client: Bob
DISCOUNTED: Keyboard, price: 270.00 lei - client: Dave

--- SORT (by client, then by price) ---
STANDARD: Mouse, price: 80.00 lei - client: Alice
STANDARD: Laptop, price: 2500.00 lei - client: Alice
DISCOUNTED: Keyboard, price: 270.00 lei - client: Dave
DISCOUNTED: Headphones, price: 160.00 lei - client: Bob
GIFT: Sticker, free - client: Charlie

--- SPECIAL (discount > 15%) ---
DISCOUNTED: Headphones, price: 160.00 lei (-20%) - client: Bob
```

---

## Instructions

Use the following Java concepts:
- `Stream.collect(groupingBy(...))` for grouping and `averagingDouble()` for averages
- `stream().filter(...).toList()` for filtering
- `Comparator.comparing(...).thenComparing(...)` for compound sorting
- `instanceof` pattern matching for identifying order type
- Custom exceptions for invalid input

---

## Testing

Demonstrate all the above operations in `Main.java`. The output must be clear and easy to follow.

A **0.4% bonus** towards the final grade is awarded if the exercise is complete and functional.