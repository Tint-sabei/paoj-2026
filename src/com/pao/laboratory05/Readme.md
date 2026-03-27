# Laboratory 04 — Records, Advanced Comparable, Multiple Comparator

> **Package:** `com.pao.laboratory05` · **Course:** 01–04 ·
> **Deadline:** Wednesday, March 25, 2026, 11:59 PM

---

<details open>
<summary><h2>Objectives</h2></summary>

1. **`record`** — syntax, what the compiler automatically generates, when to use it
2. **`record` + interfaces** — a `record` can implement `Comparable`
3. **`Comparable<T>`** — natural sorting (one per class, `compareTo`)
4. **`Comparator<T>`** — alternative sorting (any number, external classes)
5. **`Arrays.sort`** — with natural sorting and with `Comparator`
6. **Singleton + array resize** — recap of the pattern from Lab 01

> 🎯 **This time you write ALL `.java` files from scratch.**
> `Main.java` in each package is only the starting point — replace
> `System.out.println(...)` with real code after you've created the necessary classes.

</details>

---

## Files in this lab

| File | Role |
|------|------|
| [playlist/Readme.md](playlist/Readme.md) | Java Records quick reference — read before Exercise 1 |
| [playlist/Main.java](playlist/Main.java) | Exercise 1 — entry point, you create the rest |
| [biblioteca/Main.java](biblioteca/Main.java) | Exercise 2 — entry point, you create the rest |
| [angajati/Main.java](angajati/Main.java) | Exercise 3 — entry point, you create the rest |
| [audit/Main.java](audit/Main.java) | Exercise 4 Bonus — entry point, you create the rest |

---

## Exercises

| # | Package | Domain | Main Concept | Estimated Time |
|---|---------|--------|--------------|----------------|
| 1 | `playlist/` | Music Playlist | `record` + `Comparable` + `Comparator` | ~30 min |
| 2 | `biblioteca/` | Library | regular class + `Comparable` + 2 `Comparator`s + Singleton | ~35 min |
| 3 | `angajati/` | Employees | `record` for simple data + `Comparable` + Singleton + menu | ~40 min |
| 4 *(bonus)* | `audit/` | Audit Log | immutable `record` as audit entry | ~30 min |

---

## Exercise 1 — Playlist

📄 **Package:** [playlist/](playlist/) · 📖 **Pre-reading:** [playlist/Readme.md](playlist/Readme.md) — Java Records quick reference (Levels 1–4)

### What you create (all files in `com.pao.laboratory05.playlist`)

#### `Song.java` — record
```java
public record Song(String title, String artist, int durationSeconds)
        implements Comparable<Song> {
    // compareTo: sort by title (alphabetical)
    // Hint: String already has compareTo — use it
}
```

A `record` automatically generates:
- constructor with all parameters
- getters with the same name as parameters (`title()`, `artist()`, `durationSeconds()`)
- `toString()`, `equals()`, `hashCode()`

You only add `implements Comparable<Song>` and `compareTo`.

#### `SongDurationComparator.java` — external Comparator
```java
public class SongDurationComparator implements Comparator<Song> {
    // compare: sort by durationSeconds ascending
}
```

#### `Playlist.java` — class with an array of Songs
Fields:
- `private String name`
- `private Song[] songs` (initialized as `new Song[0]`)

Methods:
- `Playlist(String name)` — constructor
- `void addSong(Song song)` — add using the resize pattern (`System.arraycopy`)
- `void printSortedByTitle()` — clone the array, `Arrays.sort(copy)`, display
- `void printSortedByDuration()` — clone, `Arrays.sort(copy, new SongDurationComparator())`, display
- `int getTotalDuration()` — sum of `durationSeconds` from all songs

> ⚠️ Always clone before sorting (`Song[] copy = songs.clone()`)
> so you don't modify the original order in the playlist.

#### `Main.java` — complete with:
```java
Playlist playlist = new Playlist("Road Trip");
playlist.addSong(new Song("Waterloo", "ABBA", 174));
playlist.addSong(new Song("Bohemian Rhapsody", "Queen", 354));
playlist.addSong(new Song("Imagine", "John Lennon", 187));
playlist.addSong(new Song("Smells Like Teen Spirit", "Nirvana", 301));

System.out.println("=== " + playlist.getName() + " ===");
System.out.println("Total duration: " + playlist.getTotalDuration() + "s\n");

System.out.println("--- Sorted by title ---");
playlist.printSortedByTitle();

System.out.println("\n--- Sorted by duration ---");
playlist.printSortedByDuration();
```

<details>
<summary><b>Expected output</b></summary>

```
=== Road Trip ===
Total duration: 1016s

--- Sorted by title ---
Song[title=Bohemian Rhapsody, artist=Queen, durationSeconds=354]
Song[title=Imagine, artist=John Lennon, durationSeconds=187]
Song[title=Smells Like Teen Spirit, artist=Nirvana, durationSeconds=301]
Song[title=Waterloo, artist=ABBA, durationSeconds=174]

--- Sorted by duration ---
Song[title=Waterloo, artist=ABBA, durationSeconds=174]
Song[title=Imagine, artist=John Lennon, durationSeconds=187]
Song[title=Smells Like Teen Spirit, artist=Nirvana, durationSeconds=301]
Song[title=Bohemian Rhapsody, artist=Queen, durationSeconds=354]
```

> 📌 `toString()` for a record looks exactly `Song[title=..., artist=..., durationSeconds=...]`
> — it's automatically generated, you don't need to write it.

</details>

---

## Exercise 2 — Library

📄 **Package:** [biblioteca/](biblioteca/)

### What you create (all files in `com.pao.laboratory05.biblioteca`)

#### `Carte.java` — regular class with `Comparable`
Private fields: `String titlu`, `String autor`, `int an`, `double rating`

- Full constructor
- Getters for all fields
- `toString()` → `"Carte{titlu='...', autor='...', an=..., rating=...}"`
- `implements Comparable<Carte>` — **sort by `rating` descending**
  (highest rated appears first)

#### `CarteAnComparator.java` — external Comparator
Sort by `an` ascending (oldest appears first).

#### `CarteAutorComparator.java` — external Comparator
Sort by `autor` alphabetically.

#### `BibliotecaService.java` — Singleton
- Private constructor, `getInstance()` with internal Holder (pattern from Lab 01)
- Field: `private Carte[] carti` (initialized `new Carte[0]`)
- `void addCarte(Carte carte)` — resize + add + print confirmation
- `void listSortedByRating()` — clone, `Arrays.sort(copy)` (natural = `Comparable`), display
- `void listSortedBy(Comparator<Carte> comparator)` — clone, `Arrays.sort(copy, comparator)`, display

#### `Main.java` — complete with:
```java
BibliotecaService biblioteca = BibliotecaService.getInstance();
biblioteca.addCarte(new Carte("Ion", "Liviu Rebreanu", 1920, 4.5));
biblioteca.addCarte(new Carte("Moromeții", "Marin Preda", 1955, 4.8));
biblioteca.addCarte(new Carte("Enigma Otiliei", "George Călinescu", 1938, 4.3));
biblioteca.addCarte(new Carte("Baltagul", "Mihail Sadoveanu", 1930, 4.6));

System.out.println("\n--- By rating (descending) ---");
biblioteca.listSortedByRating();

System.out.println("\n--- By year (ascending) ---");
biblioteca.listSortedBy(new CarteAnComparator());

System.out.println("\n--- By author (alphabetical) ---");
biblioteca.listSortedBy(new CarteAutorComparator());
```

<details>
<summary><b>Expected output</b></summary>

```
Carte added: Ion
Carte added: Moromeții
Carte added: Enigma Otiliei
Carte added: Baltagul

--- By rating (descending) ---
1. Carte{titlu='Moromeții', autor='Marin Preda', an=1955, rating=4.8}
2. Carte{titlu='Baltagul', autor='Mihail Sadoveanu', an=1930, rating=4.6}
3. Carte{titlu='Ion', autor='Liviu Rebreanu', an=1920, rating=4.5}
4. Carte{titlu='Enigma Otiliei', autor='George Călinescu', an=1938, rating=4.3}

--- By year (ascending) ---
1. Carte{titlu='Ion', autor='Liviu Rebreanu', an=1920, rating=4.5}
2. Carte{titlu='Baltagul', autor='Mihail Sadoveanu', an=1930, rating=4.6}
3. Carte{titlu='Enigma Otiliei', autor='George Călinescu', an=1938, rating=4.3}
4. Carte{titlu='Moromeții', autor='Marin Preda', an=1955, rating=4.8}

--- By author (alphabetical) ---
1. Carte{titlu='Enigma Otiliei', autor='George Călinescu', an=1938, rating=4.3}
2. Carte{titlu='Ion', autor='Liviu Rebreanu', an=1920, rating=4.5}
3. Carte{titlu='Moromeții', autor='Marin Preda', an=1955, rating=4.8}
4. Carte{titlu='Baltagul', autor='Mihail Sadoveanu', an=1930, rating=4.6}
```

</details>

---

## Exercise 3 — Employees

📄 **Package:** [angajati/](angajati/)

### What you create (all files in `com.pao.laboratory05.angajati`)

#### `Departament.java` — record
```java
public record Departament(String nume, String locatie) { }
```
The record automatically generates `toString()` → `Departament[nume=..., locatie=...]`,
`equals()` (compares fields), getters `nume()` and `locatie()`.

**Note:** `findByDepartament` in the service will use `departament.nume()` for comparison,
so `equals()` on the record is used indirectly (or you can compare with `equalsIgnoreCase`).

#### `Angajat.java` — regular class with `Comparable`
Private fields: `String nume`, `Departament departament`, `double salariu`

- Full constructor
- Getters
- `toString()` → `"Angajat{nume='...', departament=Departament[nume=..., locatie=...], salariu=...}"`
- `implements Comparable<Angajat>` — **sort by `salariu` descending**
  (highest paid appears first)

#### `AngajatService.java` — Singleton
- Private constructor, `getInstance()` with internal Holder
- Field: `private Angajat[] angajati` (initialized `new Angajat[0]`)
- `void addAngajat(Angajat a)` — resize + add + print confirmation
- `void printAll()` — display all employees (array order, unsorted)
- `void listBySalary()` — clone, `Arrays.sort(copy)`, display (descending, natural)
- `void findByDepartament(String numeDept)` — traverse the array, display all employees
  whose `angajat.getDepartament().nume().equalsIgnoreCase(numeDept)`; if no employee found,
  display `"No employee in department: <numeDept>"`

#### `Main.java` — interactive menu with `Scanner`

```java
while (true) {
    System.out.println("\n===== Employee Management =====");
    System.out.println("1. Add employee");
    System.out.println("2. List by salary");
    System.out.println("3. Search by department");
    System.out.println("0. Exit");
    System.out.print("Option: ");
    // read option and execute action
}
```

Option 1 reads: `name` (String), `departmentName` (String), `departmentLocation` (String),
`salary` (double) — construct `Departament` and `Angajat`, call `addAngajat`.

<details>
<summary><b>Example interaction</b></summary>

```
===== Employee Management =====
1. Add employee
2. List by salary
3. Search by department
0. Exit
Option: 1
Name: Ana
Department (name): IT
Department (location): Cluj
Salary: 7500
Employee added: Ana

===== Employee Management =====
Option: 1
Name: Mihai
Department (name): HR
Department (location): Bucharest
Salary: 5200
Employee added: Mihai

===== Employee Management =====
Option: 1
Name: Elena
Department (name): IT
Department (location): Cluj
Salary: 8900
Employee added: Elena

===== Employee Management =====
Option: 2
--- Employees by salary (descending) ---
1. Angajat{nume='Elena', departament=Departament[nume=IT, locatie=Cluj], salariu=8900.0}
2. Angajat{nume='Ana', departament=Departament[nume=IT, locatie=Cluj], salariu=7500.0}
3. Angajat{nume='Mihai', departament=Departament[nume=HR, locatie=Bucharest], salariu=5200.0}

===== Employee Management =====
Option: 3
Department: IT
--- Employees from IT ---
Angajat{nume='Ana', departament=Departament[nume=IT, locatie=Cluj], salariu=7500.0}
Angajat{nume='Elena', departament=Departament[nume=IT, locatie=Cluj], salariu=8900.0}

===== Employee Management =====
Option: 0
Goodbye!
```

</details>

---

## Exercise 4 (Bonus) — Audit Log

📄 **Package:** [audit/](audit/)

> ⏱️ Prerequisite: finish Exercise 3 first.

### What you create (all files in `com.pao.laboratory05.audit`)

Copy the `Departament.java`, `Angajat.java` classes from `angajati/` into the `audit/` package
(adjust the `package` declaration). You will extend `AngajatService` with audit.

#### `AuditEntry.java` — record
```java
public record AuditEntry(String action, String target, String timestamp) { }
```
- `action` — what was done (ex: `"ADD"`, `"FIND_BY_DEPT"`)
- `target` — the affected object (ex: employee name or department name)
- `timestamp` — moment of the action; use `java.time.LocalDateTime.now().toString()`

#### `AngajatService.java` — Singleton with audit
Same as in Ex3, plus:
- Additional field: `private AuditEntry[] auditLog` (initialized `new AuditEntry[0]`)
- Private method `logAction(String action, String target)` — create an `AuditEntry`
  with `LocalDateTime.now().toString()` and add it to `auditLog` (resize pattern)
- `addAngajat` → call `logAction("ADD", angajat.getNume())` after adding
- `findByDepartament` → call `logAction("FIND_BY_DEPT", numeDept)` at the beginning
- `void printAuditLog()` — traverse and display all entries

#### `Main.java` — extended menu from Ex3, with extra option:
```
4. Display audit log
```

<details>
<summary><b>Example audit output</b></summary>

```
===== Employee Management (with Audit) =====
Option: 4
--- Audit Log ---
AuditEntry[action=ADD, target=Ana, timestamp=2026-03-23T10:15:32.123]
AuditEntry[action=ADD, target=Mihai, timestamp=2026-03-23T10:15:45.456]
AuditEntry[action=ADD, target=Elena, timestamp=2026-03-23T10:16:01.789]
AuditEntry[action=FIND_BY_DEPT, target=IT, timestamp=2026-03-23T10:16:10.012]
```

> 📌 `toString()` of `AuditEntry` is automatically generated by `record` —
> it looks exactly `AuditEntry[action=..., target=..., timestamp=...]`.

</details>

---

<details open>
<summary><h2>Cheat Sheet</h2></summary>

### `record` — syntax and what you get for free

```java
public record Song(String title, String artist, int durationSeconds) { }
```

| What the compiler generates | Manual equivalent |
|-----------------------------|-------------------|
| `Song(String title, String artist, int durationSeconds)` | full constructor |
| `title()`, `artist()`, `durationSeconds()` | getters (no `get` prefix!) |
| `toString()` | `"Song[title=..., artist=..., durationSeconds=...]"` |
| `equals(Object o)` | compares field by field |
| `hashCode()` | consistent with `equals` |

**Record + interface:**
```java
public record Song(String title, String artist, int durationSeconds)
        implements Comparable<Song> {
    @Override
    public int compareTo(Song other) {
        return this.title.compareTo(other.title);
    }
}
```

**When to use `record` vs regular class:**

| `record` | regular class |
|----------|---------------|
| Simple, immutable data | Need setters / mutable state |
| Don't want to write boilerplate | Want to control `toString`, `equals` |
| Transport values (DTO, log entry) | Complex business logic |

---

### `Comparable<T>` vs `Comparator<T>`

| | `Comparable<T>` | `Comparator<T>` |
|---|-----------------|-----------------|
| **Where** | in the model class | separate external class |
| **Method** | `compareTo(T o)` | `compare(T o1, T o2)` |
| **How many** | only one | any number |
| **Usage** | `Arrays.sort(arr)` | `Arrays.sort(arr, comparator)` |
| **Naming** | **natural** sorting | **alternative** sorting |

**Return rule:**

| Returns | Meaning |
|---------|---------|
| negative | `this` (or `o1`) comes **before** |
| `0` | equal |
| positive | `this` (or `o1`) comes **after** |

**Descending** — reverse the sign:
```java
// Descending by salary:
@Override
public int compareTo(Angajat other) {
    return Double.compare(other.salariu, this.salariu); // other - this = descending
}
```

**String comparison:** `this.title.compareTo(other.title)` — alphabetical ascending.

---

### `Arrays.sort` — variants

```java
Arrays.sort(arr);                          // natural (Comparable)
Arrays.sort(arr, new MyComparator());      // external Comparator
Arrays.sort(arr, (a, b) -> ...);           // lambda (anonymous Comparator)
```

**Clone before sorting** (to preserve original order):
```java
Song[] copy = songs.clone();
Arrays.sort(copy);
```

---

### Singleton pattern + resize (recap from Lab 01)

```java
public class BibliotecaService {
    private Carte[] carti = new Carte[0];

    private BibliotecaService() {}

    private static class Holder {
        private static final BibliotecaService INSTANCE = new BibliotecaService();
    }

    public static BibliotecaService getInstance() {
        return Holder.INSTANCE;
    }

    public void addCarte(Carte carte) {
        Carte[] tmp = new Carte[carti.length + 1];
        System.arraycopy(carti, 0, tmp, 0, carti.length);
        tmp[tmp.length - 1] = carte;
        carti = tmp;
    }
}
```

</details>

---

## What's next for Laboratory 05?
- Generics (`<T>`, bounded types, wildcards)
- Stream API & lambdas (introduction)
- Generic collections (`List<T>`, `Set<T>`)

---

## FAQ

<details>
<summary><b>1. Why is a record's getter called <code>title()</code> and not <code>getTitle()</code>?</b></summary>

`record` follows a different convention than regular classes — the accessor has the **same name as the field**.
Regular classes use `getTitle()` by JavaBeans convention (for frameworks like Spring/JPA).
`record` doesn't follow JavaBeans — it's a simple data structure, not a bean.

```java
Song s = new Song("Imagine", "John Lennon", 187);
s.title();           // ✅ record accessor
s.getTitle();        // ❌ doesn't exist!
```

</details>

<details>
<summary><b>2. Can I modify a record's fields after creation?</b></summary>

**No.** Records are **immutable** — fields are implicitly `final`.
There are no setters. If you need a mutable object, use a regular class.

```java
Song s = new Song("Imagine", "John Lennon", 187);
// s.title = "something else";   // ❌ compilation error — final field
```

</details>

<details>
<summary><b>3. Can I add custom methods to a record?</b></summary>

**Yes.** You can add any method, compact constructor, or implement interfaces:

```java
public record Song(String title, String artist, int durationSeconds)
        implements Comparable<Song> {

    // custom method
    public String formattedDuration() {
        return durationSeconds / 60 + ":" + String.format("%02d", durationSeconds % 60);
    }

    // implemented interface
    @Override
    public int compareTo(Song other) {
        return this.title.compareTo(other.title);
    }
}
```

</details>

<details>
<summary><b>4. Why do I clone the array before sorting?</b></summary>

`Arrays.sort` sorts **in-place** — it modifies the original array.
If you want to display multiple times in different orders without losing the insertion order, you clone:

```java
Song[] copy = songs.clone();   // new array, references to the same objects
Arrays.sort(copy);             // sorts the copy, original remains intact
```

`clone()` on an array makes a **shallow copy** — objects are not duplicated,
only references are. It's sufficient for sorting.

</details>

<details>
<summary><b>5. How do I compare <code>double</code> in <code>compareTo</code>?</b></summary>

Avoid subtraction (`a - b`) for `double` — it can give wrong results due to floating-point precision.
Use `Double.compare`:

```java
// Descending by rating:
@Override
public int compareTo(Carte other) {
    return Double.compare(other.getRating(), this.getRating());
}

// Ascending:
return Double.compare(this.getRating(), other.getRating());
```

</details>

<details>
<summary><b>6. How does the Singleton Holder work? Why not <code>static INSTANCE</code> directly?</b></summary>

```java
// ❌ Simple variant — instance is created when class loads (eager)
private static final BibliotecaService INSTANCE = new BibliotecaService();

// ✅ Holder — instance is created only on first call (lazy), thread-safe
private static class Holder {
    private static final BibliotecaService INSTANCE = new BibliotecaService();
}
```

The Holder is a static inner class — the JVM loads it **only when first accessed**,
i.e., at the first `getInstance()` call. It's guaranteed thread-safe by the JVM specification.

</details>

<details>
<summary><b>7. Connection to the individual project</b></summary>

| What you do in Lab 04 | What you will use in the project |
|-----------------------|----------------------------------|
| `record` for simple data | DTOs, audit entries |
| `Comparable` on model | natural sorting in service |
| External `Comparator` | alternative sorting on request |
| Singleton + array resize | object management services |
| Interactive menu | user interface (Main) |
| Audit log with `record` | **Stage II** — CSV audit service |

</details>