# Laboratory 04 — Collections (`Map`), Enums, Exceptions

> **Package:** `com.pao.laboratory04` · **Course:** 07–08 · **Prerequisites:** [Laboratory 03](src/com/pao/laboratory04/Readme.md)

---

<details open>
<summary><h2>Objectives</h2></summary>

1. **`Map`** — `HashMap`, `TreeMap`: key-value pairs
2. **Enums** — constants with fields, constructors, abstract methods per constant
3. **Exceptions** — `Throwable` hierarchy, checked vs unchecked, `try-catch-finally`, custom exceptions
4. **Integrator Exercise** — `Map` + `enum` + custom exceptions + Singleton

> ⚠️ This time you **are NOT given pre-made model classes**. Create everything from scratch — this simulates working on the individual project.

</details>

---

## Exercises

| # | Exercise | Time | What to create |
|---|----------|------|----------------|
| 1 | HashMap + TreeMap | ~20 min | Everything in [collections/Main.java](src/com/pao/laboratory04/collections/Main.java) |
| 2 | Enums | ~20 min | `Priority.java` + [enums/Main.java](src/com/pao/laboratory04/enums/Main.java) |
| 3 | Custom Exceptions | ~25 min | `InvalidAgeException.java`, `DuplicateEntryException.java` + [exceptions/Main.java](src/com/pao/laboratory04/exceptions/Main.java) |
| 4 | Integrator: Students + Grades | ~35 min | 6 classes from scratch + [exercise/Main.java](src/com/pao/laboratory04/exercise/Main.java) |
| 5 | **Bonus:** Task Manager + Audit | ~45 min | ~8 classes from scratch + [bonus/Main.java](src/com/pao/laboratory04/bonus/Main.java) |

---

### Exercise 1 — HashMap and TreeMap

> 📖 **Example:** First run [collections/ExampleMap.java](src/com/pao/laboratory04/collections/ExampleMap.java) to see how Maps work.

Work in [collections/Main.java](src/com/pao/laboratory04/collections/Main.java) — requirements are in Javadoc.

**Key concepts:**
- `HashMap<K, V>` — O(1) access, unpredictable order
- `TreeMap<K, V>` — **sorted** keys, O(log n) access
- `put`, `get`, `getOrDefault`, `keySet`, `values`, `entrySet`
- `Map<String, List<String>>` — map with lists as values

<details>
<summary><b>Expected output</b></summary>

```
=== PART A: HashMap — word frequency ===
Frequency: {python=2, c++=2, java=3, rust=1, go=1}
Contains 'rust'? true
Keys: [python, c++, java, rust, go]
Values: [2, 2, 3, 1, 1]
python -> 2
c++ -> 2
java -> 3
rust -> 1
go -> 1

=== PART B: TreeMap — automatic sorting ===
Sorted: {c++=2, go=1, java=3, python=2, rust=1}
First key: c++
Last key: rust

=== PART C: Map with objects ===
Students in PAOJ: [Ana, Mihai, Ion]
Students in DB (updated): [Ana, Elena, George]
```

</details>

---

### Exercise 2 — Enums with fields and methods

> 📖 **Example:** First run [enums/ExampleEnum.java](src/com/pao/laboratory04/enums/ExampleEnum.java) to see simple enums and enums with abstract methods.

Create `Priority.java` in [enums/](src/com/pao/laboratory04/enums) then complete [enums/Main.java](src/com/pao/laboratory04/enums/Main.java).

**Remember:**
- Enum = fixed set of singleton constants
- Can have fields, **private** constructor, getters, abstract methods
- `values()`, `valueOf("STRING")`, `name()`, `ordinal()`
- Compare with `==` (not `.equals()`)

<details>
<summary><b>Expected output</b></summary>

```
=== All priorities ===
🟢 LOW (level=1, color=green)
🟡 MEDIUM (level=2, color=yellow)
🟠 HIGH (level=3, color=orange)
🔴 CRITICAL (level=4, color=red)

=== Switch on priority ===
⚠️ Warning! High priority!

=== valueOf ===
Priority.valueOf("HIGH") = HIGH

=== Enum comparison ===
HIGH == HIGH? true
HIGH == LOW? false

=== name() and ordinal() ===
LOW: name=LOW, ordinal=0
MEDIUM: name=MEDIUM, ordinal=1
HIGH: name=HIGH, ordinal=2
CRITICAL: name=CRITICAL, ordinal=3
```

</details>

---

### Exercise 3 — Custom exceptions

> 📖 **Example:** First run [exceptions/ExampleExceptions.java](src/com/pao/laboratory04/exceptions/ExampleExceptions.java) to see try-catch, custom exceptions, multi-catch.

Create `InvalidAgeException.java` and `DuplicateEntryException.java` in [exceptions/](src/com/pao/laboratory04/exceptions), then complete [exceptions/Main.java](src/com/pao/laboratory04/exceptions/Main.java).

<details>
<summary><b>Exception hierarchy</b></summary>

```
                    Throwable
                   /         \
             Exception       Error (DO NOT catch)
              /       \
  checked exceptions   RuntimeException (unchecked)
  (IOException)         /      |       \
                  NullPointer  IndexOutOf  IllegalArgument
                                            \
                                     your custom exceptions
```

| Type | Compiler enforces? | Examples |
|------|-------------------|----------|
| **Checked** | ✅ Yes | `IOException`, `SQLException` |
| **Unchecked** | ❌ No | `NullPointerException`, `IllegalArgumentException` |
| **Error** | ❌ Do not catch | `OutOfMemoryError`, `StackOverflowError` |

</details>

**Remember:**
- `throw new MyException("message")` — throw
- `throws MyException` — declare in signature
- `catch (Ex1 | Ex2 e)` — multi-catch
- Order: **specific → general**
- `finally` — executes **always**

<details>
<summary><b>Expected output</b></summary>

```
=== a) Unchecked — NullPointerException ===
Caught: Cannot invoke "String.length()" because "s" is null
Finally always executes!

=== b) Custom exceptions ===
InvalidAgeException: Age -5 is invalid (0-150)
DuplicateEntryException: 'Ana' already exists in list

=== c) Multi-catch ===
Exception caught: Age 200 is invalid (0-150)

=== d) Catch ordering (specific → general) ===
InvalidAgeException caught specifically: Age -1 is invalid (0-150)

=== e) Throw vs throws ===
Method process() threw: Age 999 is invalid (0-150)
```

</details>

---

### Exercise 4 (Integrator) — Student + Grade Management

Create **6 classes from scratch** then complete TODOs in [exercise/Main.java](src/com/pao/laboratory04/exercise/Main.java). Full specs in Javadoc.

| # | Class | Package | Type |
|---|-------|---------|------|
| 1 | `Subject.java` | [exercise/model/](src/com/pao/laboratory04/exercise/model) | Enum (PAOJ, BD, SO, RC) with `fullName` + `credits` |
| 2 | `Student.java` | [exercise/model/](src/com/pao/laboratory04/exercise/model) | Class with `Map<Subject, Double> grades` |
| 3 | `InvalidStudentException.java` | [exercise/exception/](src/com/pao/laboratory04/exercise/exception) | extends RuntimeException |
| 4 | `InvalidGradeException.java` | [exercise/exception/](src/com/pao/laboratory04/exercise/exception) | extends RuntimeException |
| 5 | `StudentNotFoundException.java` | [exercise/exception/](src/com/pao/laboratory04/exercise/exception) | extends RuntimeException |
| 6 | `StudentService.java` | [exercise/service/](src/com/pao/laboratory04/exercise/service) | Singleton with `List<Student>` + 6 methods |

<details>
<summary><b>Example interaction</b></summary>

```
=== Student Management System ===

--- Menu ---
1. Add student
2. Add grade
3. Display all students
4. Top students (by average)
5. Average per subject
0. Exit
Option: 1
Name: Ana
Age: 20
Student added successfully!

Option: 2
Student name: Ana
Subject (PAOJ, BD, SO, RC): PAOJ
Grade (1-10): 9.5
Grade added!

Option: 3
1. Student{name='Ana', age=20, avg=8.75}
   PAOJ = 9.5
   BD = 8.0

Option: 4
=== Top students ===
1. Ana — average: 8.75
2. Mihai — average: 7.00

Option: 5
=== Average per subject ===
PAOJ: 8.25
BD: 8.00

Option: 1
Name: Invalid
Age: -5
Error: Age -5 is invalid (18-60)

Option: 0
Goodbye!
```

</details>

<details>
<summary><b>Hints</b></summary>

**Subject.java (enum):**
```java
public enum Subject {
    PAOJ("Advanced Object-Oriented Programming", 6),
    BD("Databases", 5),
    // ...
    ;
    // fields, constructor, getters
}
```

**Student.java — getAverage():**
```java
public double getAverage() {
    if (grades.isEmpty()) return 0;
    double sum = 0;
    for (double g : grades.values()) sum += g;
    return sum / grades.size();
}
```

**StudentService.java — Singleton:**
```java
private static StudentService instance;
private StudentService() { ... }
public static StudentService getInstance() { ... }
```

**StudentService.java — getAveragePerSubject():**
```java
Map<Subject, Double> result = new HashMap<>();
for (Subject subject : Subject.values()) {
    // collect grades from all students who have a grade for 'subject'
}
```

</details>

---

### Exercise 5 (Bonus) — Task Manager with Audit Log

Build a complete system **without code skeleton** — you only get the requirements in [bonus/Main.java](src/com/pao/laboratory04/bonus/Main.java). You decide the class structure and package organization.

**What needs to be created (~8 classes):**

| Type | Classes | What's different from Ex. 4 |
|------|---------|---------------------------|
| Model | `Task` (id, title, status, priority, assignee) | Auto-generated ID ("T001", "T002"...) |
| Enum | `Status` with abstract method `canTransitionTo(Status)` | **State machine**: TODO→IN_PROGRESS→DONE, cannot go backward |
| Enum | `Priority` with `calculateScore(int baseDays)` | Enum with **calculation logic**, not just data |
| Exceptions | `DuplicateTaskException`, `TaskNotFoundException`, `InvalidTransitionException` | `InvalidTransitionException` has **extra fields** (fromStatus, toStatus) |
| Service | `TaskService` (Singleton) with **2 Maps** + **audit log** | `Map<String, Task>` + `Map<Priority, List<Task>>` + `List<String>` |

**What makes this exercise more difficult:**
- Enum with **state machine** (valid/invalid transitions)
- Custom exception with **additional fields** (not just message)
- Service with **multiple synchronized data structures**
- **Audit log** — real pattern from enterprise applications
- **Urgency score** calculated from enum
- **Free organization** — you decide the packages

<details>
<summary><b>Hints</b></summary>

**Status enum with canTransitionTo:**
```java
public enum Status {
    TODO {
        @Override public boolean canTransitionTo(Status next) {
            return next == IN_PROGRESS || next == CANCELLED;
        }
    },
    IN_PROGRESS {
        @Override public boolean canTransitionTo(Status next) {
            return next == DONE || next == CANCELLED;
        }
    },
    DONE {
        @Override public boolean canTransitionTo(Status next) { return false; }
    },
    CANCELLED {
        @Override public boolean canTransitionTo(Status next) { return false; }
    };

    public abstract boolean canTransitionTo(Status next);
}
```

**Auto ID:**
```java
private int nextId = 1;
String id = String.format("T%03d", nextId++);  // "T001", "T002"...
```

**Audit log:**
```java
private final List<String> auditLog = new ArrayList<>();
// on each operation:
auditLog.add("[ADD] " + task.getId() + ": '" + task.getTitle() + "' (" + task.getPriority() + ")");
```

**getStatusSummary() — count per status:**
```java
Map<Status, Long> summary = new HashMap<>();
for (Status s : Status.values()) {
    long count = tasksById.values().stream()
        .filter(t -> t.getStatus() == s).count();
    // or with classic for loop
    summary.put(s, count);
}
```

</details>

---

<details open>
<summary><h2>Cheat Sheet</h2></summary>

| Concept | Syntax |
|---------|--------|
| `HashMap<K,V>` | O(1), unpredictable order |
| `TreeMap<K,V>` | **Sorted** by key |
| `map.getOrDefault(k, def)` | Returns value or default |
| `map.entrySet()` | `for (Map.Entry<K,V> e : map.entrySet())` |
| `enum` with fields | **Private** constructor, getters, abstract methods |
| `values()` / `valueOf("X")` | All constants / String → enum |
| `try-catch-finally` | `try { } catch (Ex e) { } finally { }` |
| `throw` / `throws` | Throw exception / Declare in signature |
| Multi-catch | `catch (Ex1 \| Ex2 e)` |
| Custom exception | `class MyEx extends RuntimeException { MyEx(String m) { super(m); } }` |

</details>

---

## What's next in Laboratory 05?
- Generics (`<T>`, bounded types, wildcards)
- Stream API & lambdas (introduction)
- Design patterns (Factory, Strategy)

---

## FAQ

<details>
<summary><b>1. <code>HashMap</code> vs <code>TreeMap</code> — when to use which?</b></summary>

| | `HashMap` | `TreeMap` |
|---|----------|-----------|
| **Order** | Unpredictable | **Sorted** keys |
| **Performance** | O(1) | O(log n) |
| **Null keys** | ✅ 1 null key | ❌ Not allowed |

**Element frequency:**
```java
Map<String, Integer> freq = new HashMap<>();
for (String word : words) {
    freq.put(word, freq.getOrDefault(word, 0) + 1);
}
```

**Map with lists:**
```java
Map<String, List<String>> groups = new HashMap<>();
groups.computeIfAbsent("PAOJ", k -> new ArrayList<>()).add("Ana");
```

Keys must have correct `equals()`/`hashCode()` — see [Lab 02 FAQ #4](src/com/pao/laboratory02/Readme.md).

</details>

<details>
<summary><b>2. What is an enum and when should I use it?</b></summary>

Enum = **fixed and finite** set of singleton constants.

**Use when:** fixed set of values (days, states, priorities), you want type safety, you want data/behavior per constant.

**Enum with fields and abstract methods:**
```java
public enum Priority {
    LOW(1) {
        @Override public String getEmoji() { return "🟢"; }
    },
    HIGH(3) {
        @Override public String getEmoji() { return "🔴"; }
    };

    private final int level;
    Priority(int level) { this.level = level; }
    public int getLevel() { return level; }
    public abstract String getEmoji();
}
```

**Rules:** Constructor implicitly private · Compare with `==` · `values()` = all constants · `valueOf("HIGH")` = String → enum.

</details>

<details>
<summary><b>3. Checked vs unchecked exceptions?</b></summary>

| | Checked | Unchecked |
|---|---------|-----------|
| **Superclass** | `Exception` (but NOT `RuntimeException`) | `RuntimeException` |
| **Compiler enforces** | ✅ Yes | ❌ No |
| **Cause** | External conditions (file, network) | Bugs (null, wrong index) |
| **Custom** | `extends Exception` | `extends RuntimeException` |

**Practice:** For custom exceptions, usually `extends RuntimeException` — simpler, doesn't pollute signatures.

</details>

<details>
<summary><b>4. When do I create a custom exception?</b></summary>

**Yes:** standard exceptions don't express the problem · you want differentiated catch · you want extra fields.

**No:** `IllegalArgumentException` already expresses the problem.

```java
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);  // super(msg) → Throwable.getMessage()
    }
}
```

The `super(message)` pattern is the same as `super(name, age)` from [Lab 02 Dog.java](src/com/pao/laboratory02/exercise4/model/Dog.java).

</details>

<details>
<summary><b>5. What does <code>finally</code> do?</b></summary>

Executes **always** — whether `try` succeeds, whether `catch` catches something, even if you have `return`!

```java
try {
    return;
} finally {
    System.out.println("executes anyway!");
}
```

**Modern alternative:** `try-with-resources` — `try (FileReader r = ...) { }`.

</details>

<details>
<summary><b>6. Why does catch order matter?</b></summary>

Java enters the **first** catch that matches. Specific → general, otherwise compilation error.

```java
// ✅ Correct
catch (InvalidAgeException e) { ... }    // specific
catch (RuntimeException e) { ... }       // general

// ❌ Wrong — doesn't compile
catch (RuntimeException e) { ... }       // catches EVERYTHING
catch (InvalidAgeException e) { ... }    // unreachable!
```

Multi-catch: `catch (Ex1 | Ex2 e)` — only if they are NOT in parent-child relationship.

</details>

<details>
<summary><b>7. <code>throw</code> vs <code>throws</code>?</b></summary>

| | `throw` | `throws` |
|---|---------|----------|
| **What it does** | Throws exception | Declares that method may throw |
| **Where** | Method body | Method signature |
| **Syntax** | `throw new MyEx("msg")` | `void m() throws MyEx` |

`throws` is mandatory only for checked exceptions.

</details>

<details>
<summary><b>8. <code>super</code> usage in Lab 03</b></summary>

`super` appears in custom exceptions — passes the message to `RuntimeException`:

| Class | Call | Parent |
|-------|------|--------|
| `InvalidAgeException` | `super(message)` | `RuntimeException` |
| `DuplicateEntryException` | `super(message)` | `RuntimeException` |
| `InvalidStudentException` | `super(message)` | `RuntimeException` |
| `InvalidGradeException` | `super(message)` | `RuntimeException` |
| `StudentNotFoundException` | `super(message)` | `RuntimeException` |

Same pattern as `super(name, age)` from Lab 02. Complete list → [Lab 02 FAQ #1](src/com/pao/laboratory02/Readme.md).

</details>

<details>
<summary><b>9. Enum as key in <code>HashMap</code>?</b></summary>

**Yes!** Enums are ideal — correct `hashCode()`/`equals()`, immutable. Java also provides optimized `EnumMap`:

```java
Map<Subject, Double> grades = new EnumMap<>(Subject.class);
grades.put(Subject.PAOJ, 9.5);
```

</details>

<details>
<summary><b>10. <code>List</code> vs <code>Set</code> vs <code>Map</code>?</b></summary>

```
Key → value pairs?  → Map (HashMap / TreeMap)
Uniqueness?         → Set (HashSet / TreeSet)
Order + duplicates? → ArrayList
Automatic sorting?  → TreeSet / TreeMap
```

**Project:** minimum 2 different collections, one sorted → `ArrayList` + `TreeMap`/`TreeSet`.

</details>

---

<details>
<summary><h2>Connection to the individual project</h2></summary>

| Project requirement | What you learned |
|---------------------|------------------|
| 2 different collections, 1 sorted | `HashMap`/`TreeMap` + `ArrayList` |
| 8 object types | Enums = new types |
| 10 actions/queries | CRUD service + validation with exceptions |
| Service class | Singleton from Lab 01-03 |
| Data validation | Custom exceptions |

**After Lab 03 you can start Stage I of the project!**

</details>