# Exercise 1 — Transaction Queue with LinkedList and Iterator

> **Package:** `com.pao.laboratory10.exercise1`
> **Estimated Time:** ~40 min · **Automated Tests:** Yes (`Checker.java`, 2 parts)

---

## Purpose

The banking system maintains a queue of pending transactions. Operators can add transactions as in a FIFO queue (using `ENQUEUE` / `DEQUEUE`) or as in a LIFO stack (using `PUSH` / `POP`), view the queue, and bulk-remove transactions of a specific type or below a certain threshold. Removals **must** use `Iterator.remove()` — the only safe method during iteration.

---

## General Requirements

Create the following in the package `com.pao.laboratory10.exercise1`:

* `enum TransactionType { CREDIT, DEBIT }`
* `class Transaction` with the fields: `int id`, `double amount`, `String date` (yyyy-MM-dd), `TransactionType type`
* constructors, getters, `toString()` → `[id] date type: amount RON` (e.g.: `[1] 2024-01-10 CREDIT: 500.00 RON`)


* `Main.java` with the command protocol described below, using a `LinkedList<Transaction>` internally.

---

## Input Format

Commands are read from stdin until EOF, one per line.

## Output Format

**Transaction line format:**

```
[id] date type: amount RON

```

**Available Commands:**

| Command | LinkedList Operation | Output |
| --- | --- | --- |
| `ENQUEUE id amount date type` | `addLast` | *(no output)* |
| `DEQUEUE` | `removeFirst` | `Processed: [id] date type: amount RON` or `Empty queue.` |
| `PUSH id amount date type` | `addFirst` | *(no output)* |
| `POP` | `removeFirst` | `Extracted: [id] date type: amount RON` or `Empty queue.` |
| `REMOVE_DEBIT` | `Iterator.remove()` on DEBIT | `Removed N DEBIT transactions.` |
| `REMOVE_BELOW threshold` | `Iterator.remove()` on amount < threshold | `Removed N transactions below threshold RON.` |
| `PRINT` | iteration | All transactions, one per line |
| `SIZE` | `size()` | `Queue size: N` |

---

## Part A — Basic Operations (ENQUEUE, DEQUEUE, PUSH, POP, PRINT, SIZE)

Implement the basic operations of the queue. `DEQUEUE` and `POP` both perform `removeFirst()`, but with different messages. If the queue is empty, display `Empty queue.`.

**Example:**

```
Input:                          Output:
ENQUEUE 1 500.00 2024-01-10 CREDIT
ENQUEUE 2 300.00 2024-01-15 DEBIT
SIZE                            Queue size: 2
PRINT                           [1] 2024-01-10 CREDIT: 500.00 RON
                                [2] 2024-01-15 DEBIT: 300.00 RON
DEQUEUE                         Processed: [1] 2024-01-10 CREDIT: 500.00 RON
DEQUEUE                         Processed: [2] 2024-01-15 DEBIT: 300.00 RON
DEQUEUE                         Empty queue.

```

---

## Part B — Removal with Iterator (REMOVE_DEBIT, REMOVE_BELOW)

Implement bulk removals. Use an explicit `Iterator<Transaction>` — **do not** use an `enhanced-for` loop (it would throw a `ConcurrentModificationException`).

`REMOVE_DEBIT` removes all transactions of type DEBIT.
`REMOVE_BELOW threshold` removes all transactions where `amount < threshold`.

Display the number of removed transactions, even if it is 0.

**Example:**

```
Input:                             Output:
ENQUEUE 1 500.00 2024-01-10 CREDIT
ENQUEUE 2 50.00 2024-01-15 DEBIT
ENQUEUE 3 150.00 2024-02-01 CREDIT
REMOVE_BELOW 100.00                Removed 1 transactions below 100.00 RON.
PRINT                              [1] 2024-01-10 CREDIT: 500.00 RON
                                   [3] 2024-02-01 CREDIT: 150.00 RON

```

---

## Hints

* `LinkedList<E>` implements `Deque<E>` — `addFirst` / `addLast` / `removeFirst` are **O(1)**; `ArrayList.addFirst` would be **O(n)**.
* Using an `enhanced-for` loop on a `LinkedList` while adding/removing elements → `ConcurrentModificationException`.
* `Iterator.remove()` pattern:
```java
Iterator<Transaction> itr = queue.iterator();
while (itr.hasNext()) {
    Transaction t = itr.next();
    if (condition) itr.remove();
}

```


* Use `Double.parseDouble(scanner.next())` for the amount and `TransactionType.valueOf(scanner.next())` for the type.

```</E></E></Transaction></Transaction>

```