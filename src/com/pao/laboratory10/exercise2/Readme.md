# Exercise 2 — Deduplication and Monthly Reports

> **Package:** `com.pao.laboratory10.exercise2`
> **Estimated Time:** ~40 min · **Automated Tests:** Yes (`Checker.java`, flat)

---

## Purpose

An account statement received from the bank may contain duplicate transactions (the same `id` appears twice in the export). The system must identify unique IDs (in the order of their first appearance), generate monthly reports, and sort or analyze transactions. Additionally, you will demonstrate a `ConcurrentModificationException` through a deliberate test.

---

## Import from Exercise 1

```java
import com.pao.laboratory10.exercise1.Transaction;
import com.pao.laboratory10.exercise1.TransactionType;

```

---

## Input Format

```
N
id amount date(yyyy-MM-dd) type(CREDIT|DEBIT)
... (N lines, id duplicates may exist)
command*

```

Commands are read until EOF. The internal list keeps **all** N transactions (including duplicates).

## Output Format

**Transaction line format:** `[id] date type: amount RON`

**Available Commands:**

| Command | Concept | Output |
| --- | --- | --- |
| `UNIQUE_IDS` | `LinkedHashSet<Integer>` | `Unique IDs (N): [1, 2, 3, ...]` — IDs in order of first appearance, no duplicates |
| `MONTHLY_REPORT` | `TreeMap<String, ...>` | Per sorted month: `yyyy-MM: CREDIT X.XX RON, DEBIT Y.YY RON` |
| `TOP n` | `Collections.sort` + `subList` | `Top n:` followed by n lines (descending amount, does not modify internal list) |
| `SORT_ASC` | `Collections.sort(Comparator)` | List sorted by ascending amount; modifies internal list |
| `SORT_DESC` | `Collections.sort(reversed)` | List sorted by descending amount; modifies internal list |
| `REVERSE` | `Collections.reverse` | List reversed relative to current state; modifies internal list |
| `MIN_MAX` | `Collections.min/max` | `MIN: [id] data tip: suma RON` and `MAX: [id] data tip: suma RON` |
| `CME_DEMO` | try-catch `CME` | `ConcurrentModificationException caught: modification during iteration detected.` |

> **MONTHLY_REPORT** always displays both types per month (`CREDIT 0.00 RON` if there are no CREDIT transactions in that specific month).

---

## Complete Example

```
Input:
4
1 1500.00 2024-01-15 CREDIT
2 750.50 2024-01-22 DEBIT
3 200.00 2024-02-05 CREDIT
4 1200.00 2024-02-18 DEBIT
SORT_ASC

Output:
[3] 2024-02-05 CREDIT: 200.00 RON
[2] 2024-01-22 DEBIT: 750.50 RON
[4] 2024-02-18 DEBIT: 1200.00 RON
[1] 2024-01-15 CREDIT: 1500.00 RON

```

---

## Hints

* **`LinkedHashSet<Integer>`** — Iteration yields IDs in the order of their first insertion.
* **`TreeMap<String, double[]>`** (or `Map<String, double[]>`) — Key = `date.substring(0, 7)`; Value = `[sumCREDIT, sumDEBIT]`; `TreeMap` sorts keys lexicographically (yyyy-MM sorts chronologically).
* **`Collections.sort(list, Comparator.comparingDouble(Transaction::getAmount))`** — Reuse the `Comparator` logic from Lab02/05/06.
* **`CME_DEMO`:** `try { for (Transaction t : list) list.remove(t); } catch (ConcurrentModificationException e) { System.out.println("..."); }` — The error occurs during the first iteration.
* **`TOP n`** — Create a copy of the list, sort the copy in descending order, then use `subList(0, n)` — the internal list remains unchanged.</String,></String,></String,>