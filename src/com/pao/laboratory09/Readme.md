# Laboratory 09 — Serialization, Binary I/O, and Threads

> **Package:** `com.pao.laboratory09` · **Lectures:** 08 + 09  
> **Deadline:** Wednesday, May 14, 2026, 11:59 PM

---

## Theoretical Concepts

### Java Serialization

Serialization transforms a Java object into a sequence of bytes that can be saved to a disk or transmitted over a network and later reconstructed (**deserialization**). A class becomes serializable by implementing the `java.io.Serializable` marker interface. The `static final long serialVersionUID` field ensures version compatibility. Fields marked as `transient` do not participate in serialization and receive their default value (`null` / `0`) upon deserialization.

### Binary I/O and Random Access

`DataOutputStream` wrapping `FileOutputStream` allows writing primitive types in binary format (`writeInt`, `writeDouble`, `write(byte[])`). `RandomAccessFile` enables non-sequential access: `seek(position)` moves the cursor to any byte without rewriting the entire file. Java uses **big-endian** by default; the Windows/x86 format is **little-endian** — `ByteBuffer.order(ByteOrder.LITTLE_ENDIAN)` performs the conversion.

### Threads

A thread of execution has its own stack and executes the `run()` code concurrently with other threads. `start()` creates a new stack; calling `run()` directly executes it on the current thread (a common mistake). Shared data must be protected with `synchronized`. Fields read from multiple threads should be declared `volatile`. `wait()` / `notifyAll()` allow cooperation (Producer–Consumer). `join()` makes the calling thread wait for the completion of another thread.

---

<details open>
<summary><h2>Objectives</h2></summary>

1. **Serialization** — `Serializable`, `serialVersionUID`, `transient`, `ObjectOutputStream` / `ObjectInputStream`, `try-with-resources`
2. **Binary I/O with Random Access** — `DataOutputStream`, `RandomAccessFile`, `ByteBuffer`, endianness
3. **Threads** — `Thread` / `Runnable`, `synchronized`, `volatile`, `wait()` / `notifyAll()`, `join()`, Producer–Consumer

</details>

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
|---|---------|--------------|----------------|-----------------|
| 1 | [`exercise1/`](exercise1/Readme.md) | Bank transaction serialization — `Serializable`, `transient`, `ObjectOutputStream`, `try-with-resources` | ~45 min | ✓ (3 parts) |
| 2 | [`exercise2/`](exercise2/Readme.md) | Binary registry with random access — `DataOutputStream`, `RandomAccessFile`, `ByteBuffer` little-endian | ~40 min | ✓ (flat) |
| 3 *(bonus)* | [`exercise3/`](exercise3/Readme.md) | Asynchronous processor — `Thread` / `Runnable`, `synchronized`, `volatile`, `wait()` / `notifyAll()` | ~30 min | manual |

> **Total estimated:** ~1h25 min (without bonus) · ~1h55 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Checker.java` or `exercise2/Checker.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):  
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

- **exercise1** — tests organized by parts (`partA`, `partB`, `partC`); Checker calls `IOTest.runParts`.
- **exercise2** — flat tests (`.in` / `.out` files directly in `tests/`); Checker calls `IOTest.runFlat`.

> **Intermediate files:** exercise1 creates `output/lab09_ex1.ser`, exercise2 creates `output/lab09_ex2.bin`. The `output/` directory already exists at the project root.

---

## Files in this Laboratory

| File | Role |
|------|------|
| `exercise1/Readme.md` | Exercise 1 requirements |
| `exercise1/Main.java` | Implement the requirement (complete the TODOs) |
| `exercise1/Checker.java` | Runs automated tests for exercise 1 |
| `exercise2/Readme.md` | Exercise 2 requirements |
| `exercise2/Main.java` | Implement the requirement (complete the TODOs) |
| `exercise2/Checker.java` | Runs automated tests for exercise 2 |
| `exercise3/Readme.md` | Bonus exercise requirements |
| `exercise3/Main.java` | Demonstration (complete according to the Readme requirements) |