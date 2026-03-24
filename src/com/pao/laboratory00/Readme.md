# Laboratory 00 — First Java Program

## What We Learned

### 1. The `main` Method — Entry Point
Every Java program starts with the `main` method. Its signature is always:
```java
public static void main(String[] args) {
    // your code here
}
```
- `public` — visible from anywhere (the JVM calls it from outside).
- `static` — belongs to the class, not an object (we don't need to create an instance to run it).
- `void` — returns nothing.
- `String[] args` — arguments received from the command line (optional).

### 2. The `public` Class Rule
In a `.java` file, **there can be only one `public` class**, and **the file name must match the class name**.

Example: the file `Main.java` contains `public class Main`.

### 3. What is a Package
Packages organize classes into logical folders, like a directory system.

```java
package com.pao.laboratory00;
```

- `com.pao` — the project's domain (reverse of a web domain, by convention).
- `laboratory00` — the name of the lab.
- All files in this folder **must** have the same `package` declaration on the first line.
- When you want to use a class from another package, you import it with `import`.

### 4. `Scanner` — Reading from the Keyboard
```java
Scanner scanner = new Scanner(System.in);
int n = scanner.nextInt();   // reads an integer
```
- `Scanner` comes from `java.util.Scanner` — it must be imported.
- `nextInt()` reads an `int`, `nextDouble()` reads a `double`, `nextLine()` reads a string.

### 5. Arrays — Declaration and Traversal
```java
int[] array = new int[n];          // declaration with size n

for (int i = 0; i < n; i++) { }   // classic traversal (with index)
for (int num : array) { }         // enhanced for (without index)
```

---

## Files in this Lab

| File | Role | Open |
|------|------|------|
| [Main.java](Main.java) | **Demo** — solved example by professor: reading an array + displaying in 2 ways | ⬅ Read the code and comments |
| [ArithmeticMean.java](ArithmeticMean.java) | **Exercise 1** — to be solved by student | ⬅ Write your code here |
| [MatrixDiagonals.java](MatrixDiagonals.java) | **Exercise 2** — to be solved by student | ⬅ Write your code here |

---

## Exercise 1 — Arithmetic Mean
📄 **File:** [ArithmeticMean.java](ArithmeticMean.java)

> Read from the keyboard an array of **n** integer elements.
> 1. Display the array elements in **two ways** (with index and with enhanced for).
> 2. Display the **arithmetic mean** of the array elements.

**Hints:**
- Look at `Main.java` to see how to read an array — do the same.
- Arithmetic mean = sum of elements / number of elements.
- Attention: `int / int` gives an `int` result in Java. Use a cast: `(double) sum / n`.

---

## Exercise 2 — Matrix Diagonals
📄 **File:** [MatrixDiagonals.java](MatrixDiagonals.java)

> Read from the keyboard an **n × n** matrix of **REAL** (`double`) elements.
> 1. Display the matrix in the console.
> 2. Display the **sum** of the elements on the main diagonal.
> 3. Display the **product** of the elements on the secondary diagonal.

**Hints:**
- Declare the matrix: `double[][] mat = new double[n][n];`
- Read with two nested for-loops.
- Main diagonal: elements where `i == j`.
- Secondary diagonal: elements where `i + j == n - 1`.
- Initialize the product with `1.0`, not `0`.

---

## How to Run?
1. Open the exercise file (e.g., `ArithmeticMean.java`).
2. Right-click on the `main` method → **Run**.
3. Type the values in the IntelliJ console (at the bottom) and press Enter.

---

## Quick Recap

| Concept | What to Remember |
|---------|------------------|
| `main` | Entry point — fixed signature, one per class |
| `public class` | Class name = file name, max 1 per file |
| `package` | First line in the file, reflects folder structure |
| `Scanner` | Import from `java.util`, read with `nextInt()` / `nextDouble()` / `nextLine()` |
| Array | Fixed size, traversal with index or enhanced for |