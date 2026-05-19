`implements both`List`and`Deque`. Unlike `ArrayList`, the `addFirst`/`removeFirst`operations are $O(1)$, but index-based access`get(i)`is $O(n)$. Use`LinkedList`when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods:`addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API |  |  |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]()# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API |  |  |  |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-202# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`,# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`, `partB`); Checker calls `IOTest.runParts`.
* **exercise2** — flat tests; Checker calls `IOTest.runFlat`.

# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`, `partB`); Checker calls `IOTest.runParts`.
* **exercise2** — flat tests; Checker calls `IOTest.runFlat`.

---

## Files in this Laboratory

| File | Role |
| --- | --- |
| `exercise1/Readme.md` | Requirements for Exercise 1 |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`, `partB`); Checker calls `IOTest.runParts`.
* **exercise2** — flat tests; Checker calls `IOTest.runFlat`.

---

## Files in this Laboratory

| File | Role |
| --- | --- |
| `exercise1/Readme.md` | Requirements for Exercise 1 |
| `exercise1/Main.java` | Implementation (complete the TODOs) |
| `exercise1/Checker.java` | Runs automated tests for Exercise # Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`, `partB`); Checker calls `IOTest.runParts`.
* **exercise2** — flat tests; Checker calls `IOTest.runFlat`.

---

## Files in this Laboratory

| File | Role |
| --- | --- |
| `exercise1/Readme.md` | Requirements for Exercise 1 |
| `exercise1/Main.java` | Implementation (complete the TODOs) |
| `exercise1/Checker.java` | Runs automated tests for Exercise 1 |
| `exercise2/Readme.md` | Requirements for Exercise 2 |
| `exercise2/Main.java` | Implementation (complete the TODOs# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`, `partB`); Checker calls `IOTest.runParts`.
* **exercise2** — flat tests; Checker calls `IOTest.runFlat`.

---

## Files in this Laboratory

| File | Role |
| --- | --- |
| `exercise1/Readme.md` | Requirements for Exercise 1 |
| `exercise1/Main.java` | Implementation (complete the TODOs) |
| `exercise1/Checker.java` | Runs automated tests for Exercise 1 |
| `exercise2/Readme.md` | Requirements for Exercise 2 |
| `exercise2/Main.java` | Implementation (complete the TODOs) |
| `exercise2/Checker.java` | Runs automated tests for Exercise 2 |
| `exercise3/Readme.md` | Requirements for the# Laboratory 10 — Java Collections Framework: LinkedList, Iterator, and Stream API |

> **Package:** `com.pao.laboratory10` · **Course:** 10
> **Deadline:** Wednesday, May 21, 2026, 11:59 PM

---

## Theoretical Concepts

### LinkedList — Doubly Linked List

`LinkedList<E>` implements both `List<E>` and `Deque<E>`. Unlike `ArrayList`, the `addFirst` / `removeFirst` operations are **$O(1)$**, but index-based access `get(i)` is **$O(n)$**. Use `LinkedList` when you need frequent insertions/deletions at the ends (FIFO queue or LIFO stack). Key methods: `addFirst`, `addLast`, `removeFirst`, `removeLast`, `peekFirst`, `peekLast`.

### Explicit Iterator and ConcurrentModificationException

The `enhanced-for` loop does not allow modifying a collection during iteration—any `add()` or `remove()` will throw a `ConcurrentModificationException`. The solution: `Iterator<E> itr = col.iterator(); while(itr.hasNext()) { if(...) itr.remove(); }` — `itr.remove()` is the only safe way to delete while iterating. `ConcurrentModificationException` occurs even in **single-threaded** environments, not just multi-threaded ones.

### The `Collections` Class — Utility Methods

* `Collections.sort(list, comparator)` — sorting (reuse `Comparator` from previous labs).
* `Collections.reverse(list)` — in-place reversal.
* `Collections.min(col) / max(col)` — extremes (requires `Comparable` or `Comparator`).
* `Collections.frequency(col, elem)` — number of occurrences.

### LinkedHashSet — Set with Guaranteed Order

`LinkedHashSet<E>` combines the speed of `HashSet` ($O(1)$ add/contains) with the preservation of **insertion order**. This is useful when deduplicating a collection while maintaining the order of each element's first appearance.

---

1. **LinkedList** — deque operations, structural contrast with `ArrayList`.
2. **Explicit Iterator + `itr.remove()**` — the only safe deletion during iteration.
3. **`ConcurrentModificationException`** — cause (fail-fast) and resolution.
4. **`LinkedHashSet`** — deduplication with guaranteed insertion order.
5. **`Collections` utility** — `sort`, `reverse`, `min`, `max`.
6. **Stream API** *(bonus)* — `filter`, `map`, `Collectors.groupingBy`, monthly statements.

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
| --- | --- | --- | --- | --- |
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `LinkedList` (deque operations + `Iterator.remove()`), contrast with `ArrayList` | ~40 min | ✓ (2 parts) |
| 2 | [`exercise2/`]() | `LinkedHashSet`, `ConcurrentModificationException`, `Collections.sort/reverse/min/max` | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`]() | Stream API — `filter`, `groupingBy`, `summingDouble`, monthly account statements | ~30 min | manual |

> **Total estimated:** ~1h20 min (without bonus) · ~1h50 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

* **exercise1** — tested in 2 parts (`partA`, `partB`); Checker calls `IOTest.runParts`.
* **exercise2** — flat tests; Checker calls `IOTest.runFlat`.

---

## Files in this Laboratory

| File | Role |
| --- | --- |
| `exercise1/Readme.md` | Requirements for Exercise 1 |
| `exercise1/Main.java` | Implementation (complete the TODOs) |
| `exercise1/Checker.java` | Runs automated tests for Exercise 1 |
| `exercise2/Readme.md` | Requirements for Exercise 2 |
| `exercise2/Main.java` | Implementation (complete the TODOs) |
| `exercise2/Checker.java` | Runs automated tests for Exercise 2 |
| `exercise3/Readme.md` | Requirements for the Bonus Exercise |
| `exercise3/Main.java` | Demonstration (complete according to the Readme) |