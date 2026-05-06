# Exercise 1 — Bank Transaction History Serialization

> **Package:** `com.pao.laboratory09.exercise1`  
> **Estimated time:** ~45 min · **Automated tests:** yes (`Checker.java`, 3 parts)

---

## Purpose

The Digital Bank needs to save a batch of daily transactions into a binary file and restore them later for querying. You will implement the serialization and deserialization of a complex object, observing the behavior of `transient` fields and ensuring class stability with `serialVersionUID`.

---

## General Requirements

Create in the package `com.pao.laboratory09.exercise1`:

- `enum TransactionType` with values `CREDIT` and `DEBIT`
- `class Transaction implements Serializable` with fields: `id` (int), `amount` (double), `date` (String, format `yyyy-MM-dd`), `sourceAccount` (String), `destinationAccount` (String), `type` (TransactionType), `transient String note`
  - `serialVersionUID = 1L`
  - `note` is set to `"processed"` before serialization and becomes `null` upon deserialization
- `Main.java` with the command protocol described below

**Intermediate file:** `output/lab09_ex1.ser` (relative to the project root)

---

## Input Format

```
N
id amount date(yyyy-MM-dd) sourceAccount destinationAccount type(CREDIT|DEBIT)
... (N lines)
command*
```

Commands are read until EOF.

## Output Format

**Transaction line format** (used by `LIST` and `FILTER`):
```
[id] date type: amount RON | sourceAccount -> destinationAccount
```

**Available commands:**

| Command | Output |
|---------|--------|
| `LIST` | All deserialized transactions, in serialization order |
| `FILTER yyyy-MM` | Transactions with `date` starting with `yyyy-MM`, or `No results.` |
| `NOTE id` | `NOTE[id]: <value of note field>` or `NOTE[id]: not found` |

---

## Part A — Serialization and LIST

Read N transactions, set `note = "processed"` on each, serialize the list to `output/lab09_ex1.ser`, deserialize it, then execute the `LIST` command.

**Example:**
```
Input:                       Output:
3                            [1] 2024-01-15 CREDIT: 1500.00 RON | RO01SRC1 -> RO01DST1
1 1500.00 2024-01-15 ...     [2] 2024-01-22 DEBIT: 750.50 RON | RO02SRC2 -> RO02DST2
...                          [3] 2024-02-05 CREDIT: 200.00 RON | RO01SRC1 -> RO03DST3
LIST
```

> The output order is the original insertion order (serialization preserves list order).

---

## Part B — Monthly Filtering with FILTER

Additional command: `FILTER yyyy-MM` — displays transactions whose `date` field starts with the given prefix.

If no transaction exists for that month, display:
```
No results.
```

**Example (no results):**
```
Input:          Output:
...             No results.
FILTER 2024-03
```

---

## Part C — The transient field and NOTE

Additional command: `NOTE id` — displays the value of the `note` field of the transaction with the given `id`, after deserialization.

Since `note` is `transient`, its value becomes `null` upon deserialization, regardless of it being set to `"processed"` before serialization.

**Example:**
```
Input:          Output:
...             NOTE[1]: null
NOTE 1          NOTE[99]: not found
NOTE 99
```

> **Remember:** the `transient` field does not participate in serialization. Upon deserialization, it receives the default value (`null` for objects, `0` for primitives, `false` for booleans).

---

## Hints

- `ObjectOutputStream(new FileOutputStream(...))` / `ObjectInputStream(new FileInputStream(...))` — use `try-with-resources` for both
- `serialVersionUID` explicitly declared — without it, the JVM automatically calculates an unstable UID that can change upon recompilation
- `transient` on the `note` field — marks the field as excluded from serialization
- Does `TransactionType` need to be `Serializable`? No need to implement it — Java enums are implicitly serializable
- `List<Transaction>` implements `Serializable` (as does `ArrayList`) — you can serialize the list directly</Transaction>