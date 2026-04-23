# Exercise 2 (BONUS) — Filtering and Writing to a File

> **Package:** `com.pao.laboratory08.exercise2`
> **Estimated time:** ~25 min · **No automated tests**

---

## Purpose

You will reuse the `Student` and `Adresa` classes from Exercise 1 and add **file writing** functionality using `BufferedWriter`. The program reads students from `studenti.txt`, filters them based on an age threshold read from stdin, writes the results to an output file, and displays a summary in the console.

---

## Import from Exercise 1

```java
import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;
```

---

## Data File

The same as in Exercise 1: `src/com/pao/laboratory08/tests/studenti.txt`

---

## Input Protocol (stdin)

A single line containing an integer — the minimum age threshold:

```
20
```

---

## What to do

1. Read the students from `studenti.txt` using `BufferedReader` (same as Ex 1).
2. Read the age threshold from stdin using `Scanner`.
3. Filter the students where `varsta >= threshold`.
4. Write the filtered students to `rezultate.txt` (in the current directory) using `BufferedWriter`.
   - Each student on a new line, formatted identically to `toString()`.
5. Display in the console:
   - `Filtru: varsta >= <threshold>`
   - `Rezultate: <N> studenti`
   - A blank line
   - The filtered students, one per line
   - A blank line
   - `Scris in: rezultate.txt`

---

## Example

**Input (stdin):**
```
20
```

**Output (stdout):**
```
Filtru: varsta >= 20
Rezultate: 4 studenti

Student{nume='Mihai', varsta=22, adresa=Adresa{oras='Cluj', strada='Strada Mărășești'}}
Student{nume='Elena', varsta=20, adresa=Adresa{oras='Iași', strada='Bulevardul Independenței'}}
Student{nume='Maria', varsta=23, adresa=Adresa{oras='Brașov', strada='Strada Republicii'}}
Student{nume='Andrei', varsta=21, adresa=Adresa{oras='Constanța', strada='Bulevardul Mamaia'}}

Scris in: rezultate.txt
```

**The file `rezultate.txt` (created, but NOT automatically verified):**
```
Student{nume='Mihai', varsta=22, adresa=Adresa{oras='Cluj', strada='Strada Mărășești'}}
Student{nume='Elena', varsta=20, adresa=Adresa{oras='Iași', strada='Bulevardul Independenței'}}
Student{nume='Maria', varsta=23, adresa=Adresa{oras='Brașov', strada='Strada Republicii'}}
Student{nume='Andrei', varsta=21, adresa=Adresa{oras='Constanța', strada='Bulevardul Mamaia'}}
```

---

## Hints

- Reuse the classes from Exercise 1 (import them).
- `BufferedWriter fout = new BufferedWriter(new FileWriter("rezultate.txt"))`
- Don't forget `fout.newLine()` or `fout.write("\n")` after each line.
- Don't forget `fout.close()` (or use try-with-resources if you have read the upcoming theory section).
- The `rezultate.txt` file is not automatically checked — verify its content manually.