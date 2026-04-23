# Exercise 1 — Cloning and Reading from a File

> **Package:** `com.pao.laboratory08.exercise1`
> **Estimated time:** ~1h · **Automated tests:** yes (`Checker.java`, 3 parts)

---

## Purpose

You will read student data from a text file using `BufferedReader`, construct `Student` and `Adresa` (Address) objects, and then demonstrate the difference between **shallow cloning** and **deep cloning** using the `Cloneable` marker interface.

---

## Data File

The program **always** reads from `src/com/pao/laboratory08/tests/studenti.txt`.
The path is relative to the project root (working directory = `paoj-2026/`).

CSV Format — each line: `Name,Age,City,Street`

```
Ana,19,București,Calea Victoriei
Mihai,22,Cluj,Strada Mărășești
...
```

---

## Classes to Create

### `Adresa`

```java
public class Adresa implements Cloneable {
    private String oras;
    private String strada;

    // constructor(String oras, String strada)
    // getters, setters
    // toString() → "Adresa{oras='...', strada='...'}"

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

### `Student`

```java
public class Student implements Cloneable {
    private String nume;
    private int varsta;
    private Adresa adresa;

    // constructor(String nume, int varsta, Adresa adresa)
    // getters, setters
    // toString() → "Student{nume='...', varsta=..., adresa=Adresa{oras='...', strada='...'}}"

    // clone() — different implementation for shallow vs. deep (see below)
}
```

---

## Input Protocol (stdin)

The program reads **a single line** from stdin, which is a command:

| Command | Description |
|---------|-----------|
| `PRINT` | Displays all students read from the file |
| `SHALLOW <name>` | Shallow clone of the student with the given name, modify the clone's city, display |
| `DEEP <name>` | Deep clone of the student with the given name, modify the clone's city, display |

---

## Part A — Reading from File and Displaying

**Command (stdin):** `PRINT`

**What to do:**
1. Open `studenti.txt` using `new BufferedReader(new FileReader(...))`
2. Read line by line, parse the CSV, create `Student` + `Adresa` objects
3. Close the file
4. Display all students, one per line

**Output:**
```
Student{nume='Ana', varsta=19, adresa=Adresa{oras='București', strada='Calea Victoriei'}}
Student{nume='Mihai', varsta=22, adresa=Adresa{oras='Cluj', strada='Strada Mărășești'}}
Student{nume='Elena', varsta=20, adresa=Adresa{oras='Iași', strada='Bulevardul Independenței'}}
Student{nume='Ion', varsta=17, adresa=Adresa{oras='Timișoara', strada='Strada Florilor'}}
Student{nume='Maria', varsta=23, adresa=Adresa{oras='Brașov', strada='Strada Republicii'}}
Student{nume='Andrei', varsta=21, adresa=Adresa{oras='Constanța', strada='Bulevardul Mamaia'}}
```

---

## Part B — Shallow Clone

**Command (stdin):** `SHALLOW Ana`

**What to do:**
1. Read students from the file (same as Part A)
2. Parse the command — extract the name `Ana`
3. Find the student with that name
4. Implement `clone()` in `Student` **using only `super.clone()`** (shallow)
5. Clone the found student
6. Modify the **clone's city** to `"MODIFICAT"`
7. Display the original and the clone

**Output (shallow — both display `MODIFICAT`):**
```
Original: Student{nume='Ana', varsta=19, adresa=Adresa{oras='MODIFICAT', strada='Calea Victoriei'}}
Clona: Student{nume='Ana', varsta=19, adresa=Adresa{oras='MODIFICAT', strada='Calea Victoriei'}}
```

> ⚠️ Note: the original was affected! This is the problem with shallow cloning.

---

## Part C — Deep Clone

**Command (stdin):** `DEEP Ana`

**What to do:**
1. Read students from the file (same as Part A)
2. Parse the command — extract the name `Ana`
3. Find the student, but now `Student.clone()` performs a **deep clone**:
   ```java
   @Override
   public Object clone() throws CloneNotSupportedException {
       Student clona = (Student) super.clone();
       clona.setAdresa((Adresa) this.adresa.clone());
       return clona;
   }
   ```
4. Clone, modify the clone's city to `"MODIFICAT"`
5. Display the original and the clone

**Output (deep — original keeps its initial city):**
```
Original: Student{nume='Ana', varsta=19, adresa=Adresa{oras='București', strada='Calea Victoriei'}}
Clona: Student{nume='Ana', varsta=19, adresa=Adresa{oras='MODIFICAT', strada='Calea Victoriei'}}
```

> ✅ The original was not affected — deep cloning works correctly.

---

## How does Part B differ from Part C?

The only difference is the implementation of the `clone()` method in the `Student` class:

| Part | `Student.clone()` | Result |
|-------|-------------------|----------|
| B | `return super.clone();` | Shallow — original is modified |
| C | `super.clone()` + `clona.setAdresa((Adresa) adresa.clone())` | Deep — original remains intact |

> 💡 **Practical Tip:** You can use `Main.java` with a single program that decides behavior based on the command (`SHALLOW` vs. `DEEP`). When the command is `SHALLOW`, the `clone()` method in `Student` only performs `super.clone()`. When it is `DEEP`, it also clones the address. A simple way: two different methods (`shallowClone()` and `deepClone()`) or a boolean parameter.

---

## Hints

- Use `line.split(",")` to parse each CSV line
- Use `Integer.parseInt(parts[1].trim())` for the age
- Store students in an `ArrayList<Student>`
- `Adresa` must implement `Cloneable` and have a public `clone()` — otherwise deep clone won't work
- Parse the command with `split(" ", 2)` — the first element is the type, the second is the name

---

## Automated Testing

Open `Checker.java` and press **Run** in IntelliJ.
The tests are located in `tests/partA/`, `tests/partB/`, `tests/partC/`.
The working directory must be the project root (`paoj-2026/`).