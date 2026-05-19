# Exercise 3 (BONUS) — Monthly Account Statements with Stream API

> **Package:** `com.pao.laboratory10.exercise3`
> **Estimated Time:** ~30 min · **No automated tests** — demonstration in `Main.java`

---

## Purpose

Generating monthly account statements using the Stream API — the final step in the DigitalBank narrative. 
The data is already in memory; now you will analyze it elegantly without explicit loops, 
using `filter`, `map`, `Collectors.groupingBy`, and `summingDouble`. 
This completes the "generate invoices grouped by month" operation planned for the system.

---

## Concepts Demonstrated

* `stream().filter()` — filtering based on criteria
* `stream().mapToDouble().sum()` — numerical aggregation
* `Collectors.groupingBy()` + `Collectors.summingDouble()` — grouping + summation per group
* `stream().sorted().limit()` — sorting and truncation
* `stream().map().distinct().collect()` — projection + deduplication
* `stream().mapToDouble().average()` — average calculation
* Streams are **lazy and single-use** — you must start a new `stream()` from the source for each operation

---

## Minimum Requirements for `Main.java`

Define at least 10 hardcoded transactions covering at least 3 different months, including both CREDIT and DEBIT types.

Demonstrate **7 operations**, each preceded by a title displayed in the console:

| # | Operation | Output |
| --- | --- | --- |
| 1 | `filter(type == CREDIT)` | List of all CREDIT transactions |
| 2 | `mapToDouble(amount).sum()` | `Total processed: X.XX RON` |
| 3 | `Collectors.groupingBy(month, summingDouble(amount))` | Per month: `yyyy-MM: X.XX RON` |
| 4 | `sorted(comparingDouble.reversed()).limit(3)` | `Top 3 transactions:` + 3 lines |
| 5 | `map(sourceAccount).distinct().collect(toList())` | `Unique source accounts: [ACC_A, ACC_B, ...]` |
| 6 | `mapToDouble(amount).average()` | `Average amount: X.XX RON` |
| 7 | `Collectors.groupingBy(month)` with statement format | `ACCOUNT STATEMENT - yyyy-MM: N transactions, total: X.XX RON` per month |

> **Note on Operation 5:** For this demonstration, extend the local `Transaction` class with a `sourceAccount` field or use your own structure — you have total implementation freedom.

---

## Implementation Freedom

The output order, exact formatting, and demo data are chosen by the student. **Important:** All 7 operations must be demonstrated, and the output must be clearly labeled with a title before each block of results.

---

## Hints

* Grouping by month: `Collectors.groupingBy(t -> t.getDate().substring(0, 7))`
* Sum per group: `Collectors.groupingBy(key, Collectors.summingDouble(Transaction::getAmount))`
* `OptionalDouble avg = stream().mapToDouble(...).average()` — use `.getAsDouble()` or `.orElse(0.0)`
* Streams are lazy and single-use — a `stream()` can be consumed only once; create a new one for every operation.
* Use `Collectors.toUnmodifiableList()` or `Collectors.toList()` for collection.
* Use a `TreeMap` on the `groupingBy` result if you want sorted months: `Collectors.groupingBy(..., TreeMap::new, Collectors.summingDouble(...))`