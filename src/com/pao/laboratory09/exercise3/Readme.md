# Exercise 3 (BONUS) — Asynchronous Bank Transaction Processor

> **Package:** `com.pao.laboratory09.exercise3`  
> **Estimated time:** ~30 min · **No automated tests** — demonstration in `Main.java`

---

## Purpose

Three ATMs send transactions in parallel to a shared belt (queue) with a fixed capacity of 5. A "Processor" thread consumes them and generates invoice confirmations. You will implement the **Producer–Consumer** pattern using `synchronized`, `wait()`, and `notifyAll()`.

---

## Demonstrated Concepts

- `Thread` and `Runnable` — two ways of creating threads
- `synchronized` on queue methods — mutual exclusion
- `wait()` — the producer suspends when the belt is full; the consumer suspends when it is empty
- `notifyAll()` — wakes up all waiting threads after each operation
- `volatile boolean active` — graceful shutdown of the consumer
- `join()` — the main thread waits for all producers to finish



---

## Classes to Create

**`TransactionQueue`** — shared belt, maximum capacity 5:
- The methods `add(Transaction t)` and `extract()` are `synchronized`
- `add` calls `wait()` as long as the belt is full, `notifyAll()` after adding
- `extract` calls `wait()` as long as the belt is empty, `notifyAll()` after extraction

**`ATMThread extends Thread`** — producer: 
- Receives an id (1, 2, 3) and produces 4 transactions
- `Thread.sleep(50)` between consecutive transactions
- Displays `[ATM-N] sends: Transaction #id amount RON` upon each send
- Optional: `[ATM-N] waiting for space...` before `wait()` in `add`

**`ProcessorThread implements Runnable`** — consumer:
- `volatile boolean active` — the loop runs as long as `active == true`
- `Thread.sleep(80)` between processings
- Displays `[Processor] Invoice #id - amount RON | date` upon each processing

---

## Minimum Requirements for `Main.java`

1. Create 3 instances of `ATMThread` and one `ProcessorThread` on a separate thread
2. Start all producers with `start()`
3. Start the consumer with `new Thread(processorThread).start()`
4. Call `join()` on all 3 ATMs (the main thread waits for them to finish)
5. Set `processorThread.active = false` and call `notifyAll()` on the queue (to wake the consumer from a potential `wait()`)
6. Call `join()` on the consumer thread
7. Display `All transactions processed. Total: 12`

**The output demonstrates:**
- 12 lines of `[ATM-N] sends:` (3 ATMs × 4 transactions)
- 12 lines of `[Processor] Invoice #...`
- At least one line of `[ATM-N] waiting for space...` (the belt fills up at some point)
- The final line `All transactions processed. Total: 12`

---

## Implementation Freedom

Test data can be hardcoded or generated (sequential IDs, random amounts, fixed dates). The order of output lines may vary (concurrent threads). Important: all 12 transactions appear in the output and the consumer generates an invoice for each.

---

## Hints

- `start()` vs `run()` — calling `run()` directly does not create a new thread; all code runs on the current thread
- `synchronized` on a non-static method → lock on the **object** (on the `TransactionQueue` instance)
- `wait()` and `notify()` are called **only within a `synchronized` block**, otherwise `IllegalMonitorStateException` occurs
- `notifyAll()` is preferable to `notify()` — it wakes up all threads, not just a random one
- `volatile boolean active` — without `volatile`, the consumer thread might work with a local copy of the variable (from CPU cache) and not see the modification made in the main thread