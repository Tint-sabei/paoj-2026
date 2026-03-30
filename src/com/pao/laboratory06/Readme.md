# Laboratory 06 — Interfaces

> **Package:** `com.pao.laboratory06` · **Course:** 05 ·
> **Deadline:** Wednesday, April 1, 2026, 23:59

---

<details open>
<summary><h2>Objectives</h2></summary>

1. **Interfaces** — syntax, members (constants, abstract methods, default, static, private)
2. **`Comparable<T>`** — natural sorting, `compareTo`
3. **`Comparator<T>`** — alternative sorting, external classes
4. **Callback** — passing a method as an argument using interfaces
5. **Extending interfaces** — `extends` between interfaces, sub-interfaces
6. **Interface type reference** — accessing an object through the interface type (CAN_DO)
7. **Constants in interfaces** — constant groups (and why enums are superior)

</details>

---

## Exercises

| # | Package | Main Concept | Estimated Time | Automated Tests |
|---|---------|--------------|----------------|-----------------|
| 1 | [`exercise1/`](exercise1/Readme.md) | EXAMPLE: `Comparable` + `Comparator` + multiple sorts | ~35 min | ✓ (3 parts) |
| 2 | [`exercise2/`](exercise2/Readme.md) | Main requirements (see main Readme) | ~30 min | ✓ (3 parts) |
| 3 (bonus) | [`exercise3/`](exercise3/Readme.md) | Advanced collaborator integration (bonus) | ~45 min | manual |

> **Note:** Only in this laboratory, exercise 1 is an example to familiarize you with the format and automated testing. Exercise 2 is the main exercise, with requirements based on the main laboratory Readme. Exercise 3 is a bonus: if you solve it, you can participate in only 8 exercises and receive a 0.5% bonus on the final laboratory grade, allowing you to reach the maximum grade even if it doesn't round up to 50% in the final calculation.

> **Estimated total:** ~1h50 min (without bonus) · ~2h35 min (with bonus)

---

## How to Run Automated Tests

Open `exercise1/Test.java` or `exercise2/Test.java` in IntelliJ and press **Run**.

The working directory must be the **project root** (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

Each exercise with automated tests has **multiple parts** (`partA`, `partB`, `partC`).
You can solve and test one part at a time — when running, you'll see a summary per part
and a final table with the total number of tests passed.

---

## Files in this Laboratory

| File | Role |
|------|------|
| [exercise1/Readme.md](exercise1/Readme.md) | Complete requirements Ex 1 (example) |
| [exercise1/Main.java](exercise1/Main.java) | Entry point Ex 1 |
| [exercise1/Test.java](exercise1/Test.java) | Automated test runner Ex 1 |
| [exercise2/Readme.md](exercise2/Readme.md) | Complete requirements Ex 2 (main) |
| [exercise2/Main.java](exercise2/Main.java) | Entry point Ex 2 |
| [exercise2/Test.java](exercise2/Test.java) | Automated test runner Ex 2 |
| [exercise3/Readme.md](exercise3/Readme.md) | Complete requirements Ex 3 (bonus) |
| [exercise3/Main.java](exercise3/Main.java) | Entry point Ex 3 |