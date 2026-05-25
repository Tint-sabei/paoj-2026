Here is the English translation of your assignment text:

---

# Exercise 3 (Bonus) - Custom Collector and Immutable Snapshot

## Scenario

Extend the reporting module with a custom collector that produces a read-only analytical snapshot.

## Concepts Demonstrated

* `Collector.of(...)`
* Immutable models for results
* Separation between collection and presentation

## Minimum Requirements

1. Define a custom collector for multi-dimensional aggregation.
2. Return an immutable result (no mutations allowed after construction).
3. Demonstrate at least 3 queries on the snapshot in `Main.java`.

## Freedom of Implementation

* There is no fixed I/O format.
* Data can be hardcoded or generated internally.
* Evaluation is manual, based on the clarity of the demo and the correctness of the result.

## Implementation Example (Suggestion)

Below is a minimal, fully functional example illustrating a custom `Collector`, a mutable container used during collection, and an immutable `Snapshot` returned by the finisher. This code serves as a guideline — you may use the same ideas in your own implementation.

1. Simple `Transaction` model (immutable):

```java
public final class Transaction {
    private final int id;
    private final BigDecimal amount;
    private final LocalDate date;
    private final String country;
    private final String channel;

    public Transaction(int id, BigDecimal amount, LocalDate date, String country, String channel) {
       this.id = id; this.amount = amount; this.date = date; this.country = country; this.channel = channel;
    }
    // getters...
}

```

2. Immutable `Snapshot` for aggregated results:

```java
public final class Snapshot {
    private final Map<String, Long> countByCountry;
    private final Map<String, Long> countByChannel;
    private final BigDecimal totalAmount;
    private final List<Transaction> topTransactions;

    public Snapshot(Map<String, Long> byCountry, Map<String, Long> byChannel, BigDecimal total, List<Transaction> top) {
       this.countByCountry = Collections.unmodifiableMap(new HashMap<>(byCountry));
       this.countByChannel = Collections.unmodifiableMap(new HashMap<>(byChannel));
       this.totalAmount = total;
       this.topTransactions = List.copyOf(top);
    }
    // getters...
}

```

3. `CustomCollectors.toSnapshot(int topN)` — custom collector:

```java
public static Collector<Transaction, ?, Snapshot> toSnapshot(int topN) {
    class Agg { /* mutable maps, total, list */ }
    return Collector.of(
       Agg::new,
       (agg, tx) -> { /* accumulate */ },
       (a,b) -> { /* combine for parallel */ return a; },
       agg -> { /* finisher -> build Snapshot, compute topN */ }
    );
}

```

4. `Main` demo — at least 3 queries on the `Snapshot`:

```java
List<Transaction> data = List.of(/* a few hardcoded transactions */);
Snapshot snap = data.stream().collect(CustomCollectors.toSnapshot(5));

// Query 1: top transactions (from the snapshot)
snap.getTopTransactions().forEach(System.out::println);

// Query 2: total by country (descending)
snap.getCountByCountry().entrySet().stream() /* sort & print */;

// Query 3: channels sorted by count
snap.getCountByChannel().entrySet().stream() /* sort & print */;

```

Comments:

* The finisher `agg -> Snapshot` must transform the mutable structures from `Agg` into immutable collections and compute the views (e.g., topN).
* `Collector.Characteristics.UNORDERED` is acceptable if you do not depend on the stream order; ensure that `combine` is correctly implemented for parallel executions.
* Test with data that produces tie-breakers (e.g., two transactions with the same amount) to demonstrate the ordering stability within the snapshot.