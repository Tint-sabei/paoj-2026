# Laboratory 08 — Marker Interfaces, Cloning, and I/O Streams (Introduction)

> **Package:** `com.pao.laboratory08`
> **Deadline:** Wednesday, April 25, 2026, at 23:59

-----

## Summary

| \# | Package | Main Concept | Estimated Time | Automated Tests |
|---|--------|-------------------|--------------|----------------|
| 1 | [`exercise1/`](https://www.google.com/search?q=exercise1/Readme.md) | `BufferedReader` + `Cloneable`, shallow vs. deep clone — Student domain | \~1h | ✓ (3 parts) |
| 2 *(bonus)* | [`exercise2/`](https://www.google.com/search?q=exercise2/Readme.md) | `BufferedWriter` / `FileWriter` — filtering and writing students to file | \~25 min | manual |

> **Estimated Total:** \~1h (without bonus) · \~1h25 min (with bonus)

-----

## Theoretical Notions

### Marker Interfaces

A **marker interface** is an interface with no methods — its role is to associate metadata with a class, which the JVM uses at runtime:

- `java.lang.Cloneable` — allows the call to `Object.clone()`; without it, the JVM throws `CloneNotSupportedException`.
- `java.io.Serializable` — allows object serialization (conversion into a byte stream).

### Shallow vs. Deep Cloning

- **Shallow cloning** — `super.clone()` copies primitive fields and references, but **not** the referenced objects themselves. Modifying an object field in the clone affects the original as well.
- **Deep cloning** — `clone()` is redefined at every reference level, creating independent copies.

<!-- end list -->

```java
// Deep clone:
@Override
public Object clone() throws CloneNotSupportedException {
    Student clona = (Student) super.clone();
    clona.setAdresa((Adresa) this.adresa.clone()); // independent copy
    return clona;
}
```

### I/O Streams — Introduction

| Stream | Type | When to Use |
|------|-----|-------------------|
| `FileReader` / `FileWriter` | character | Small text files, character by character |
| `BufferedReader` / `BufferedWriter` | character + buffer | **Recommended** — text files, line by line |
| `FileInputStream` / `FileOutputStream` | byte | Binary files |

```java
BufferedReader fin = new BufferedReader(new FileReader("studenti.txt"));
BufferedWriter fout = new BufferedWriter(new FileWriter("rezultate.txt"));
String linie;
while ((linie = fin.readLine()) != null) {
    fout.write(linie);
    fout.newLine();
}
fin.close();
fout.close();
```

### `Serializable` *(preview — mandatory in Lab 09)*

- Declare `private static final long serialVersionUID` for compatibility between versions.
- `transient` fields are **not** serialized.

-----

## ⏭️ What's Next in Laboratory 09

> 📖 Detailed Theory: [`theory1/Readme.md`](https://www.google.com/search?q=theory1/Readme.md)

- **`Serializable`** in depth: `serialVersionUID`, `transient`, `ObjectInputStream`/`ObjectOutputStream`.
- **`DataInputStream` / `DataOutputStream`** — primitive types in binary files.
- **`BufferedInputStream` / `BufferedOutputStream`** — buffering for binary streams.
- **`RandomAccessFile`** — random access, `seek()`, BMP image processing.
- **`ByteBuffer`** and endianness — big-endian ↔ little-endian.
- **`try-with-resources`** — automatic closing of `AutoCloseable` resources.

-----

## How to Run Automated Tests

Open `exercise1/Test.java` in IntelliJ and press **Run**.
The working directory must be the project root (`paoj-2026/`):
`Run → Edit Configurations → Working directory → $PROJECT_DIR$`

- **exercise1**: tests in parts (`partA`, `partB`, `partC`); they read from `tests/studenti.txt` via `BufferedReader`, additional parameters from `stdin`.
- **exercise2** (bonus): no automated tests — manually verify the output and `rezultate.txt`.

-----

## Files in this Laboratory

| File | Role |
|--------|-----|
| [tests/studenti.txt](https://www.google.com/search?q=tests/studenti.txt) | Shared CSV file — read by both exercises |
| [exercise1/Readme.md](https://www.google.com/search?q=exercise1/Readme.md) | Full requirement for Ex 1 |
| [exercise1/Main.java](https://www.google.com/search?q=exercise1/Main.java) | Entry point for Ex 1 |
| [exercise1/Test.java](https://www.google.com/search?q=exercise1/Test.java) | Automated test runner for Ex 1 |
| [exercise2/Readme.md](https://www.google.com/search?q=exercise2/Readme.md) | Full requirement for Ex 2 (bonus) |
| [exercise2/Main.java](https://www.google.com/search?q=exercise2/Main.java) | Entry point for Ex 2 (bonus) |
| [theory1/Readme.md](https://www.google.com/search?q=theory1/Readme.md) | Detailed theory for Lab 09 |