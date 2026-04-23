# Advanced I/O Theory for Laboratory 09

> **Goal:** This section provides detailed theory for the bonus exercise in Lab 08 and sets the stage for Laboratory 09.
>
> If you don't have time to solve the bonus exercise now, read this theory anyway — you will need it next week.

---

## The `Serializable` Interface — Object Serialization

### What is Serialization?

**Serialization** is the process of converting a Java object into a byte stream, which can be saved to a binary file or transmitted over a network. **Deserialization** is the reverse process — reconstructing the object from the byte stream.



### The `java.io.Serializable` Marker Interface

For a class to be serializable, it must implement the `Serializable` marker interface:

```java
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nume;
    private int varsta;
    private transient String parola; // Will NOT be serialized
    
    // constructor, getters, setters
}
```

### The `serialVersionUID` Field

This is a version identifier for the class. The JVM uses it to verify that the deserialized object is compatible with the current version of the class.

- If missing, the JVM generates it automatically (it can be unstable between compilations).
- **Best practice:** always declare it explicitly:

```java
private static final long serialVersionUID = 1L;
```

When you change the class structure (add/remove fields), increment the value to indicate incompatibility.

### `transient` Fields

Marks fields that **should not** be serialized:

```java
private transient String parola;           // sensitive data
private transient Socket conexiune;        // non-serializable resources
private transient int counterTemporar;     // temporary data
```

During deserialization, `transient` fields receive default values (`null`, `0`, `false`).

---

## Reading and Writing Serialized Objects

### `ObjectOutputStream` — Writing

```java
try (FileOutputStream fout = new FileOutputStream("studenti.ser");
     ObjectOutputStream out = new ObjectOutputStream(fout)) {
    
    Student s = new Student("Ana", 20);
    out.writeObject(s);  // serializes the object
    
} catch (IOException e) {
    e.printStackTrace();
}
```

### `ObjectInputStream` — Reading

```java
try (FileInputStream fin = new FileInputStream("studenti.ser");
     ObjectInputStream in = new ObjectInputStream(fin)) {
    
    Student s = (Student) in.readObject();  // deserializes
    System.out.println(s.getNume());        // "Ana"
    
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

### Serialization of Aggregation/Composition

If the `Student` class contains an `Adresa` field, then `Adresa` must also be `Serializable`:

```java
public class Adresa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String oras;
    private String strada;
}

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nume;
    private Adresa adresa;  // OK — Adresa is Serializable
}
```

If `Adresa` is not `Serializable`, a `NotSerializableException` is thrown at runtime.

---

## `DataInputStream` / `DataOutputStream` — Reading/Writing Primitive Data

These allow for **formatted** reading/writing of primitive types (`int`, `double`, `String`) from/to binary files without serializing entire objects.

### Writing

```java
try (DataOutputStream out = new DataOutputStream(
        new FileOutputStream("date.bin"))) {
    
    out.writeInt(42);
    out.writeDouble(3.14);
    out.writeUTF("Ana");      // String in UTF-8 format
    out.writeBoolean(true);
    
} catch (IOException e) {
    e.printStackTrace();
}
```

### Reading

**⚠️ Important:** You must read **in the same order** as you wrote!

```java
try (DataInputStream in = new DataInputStream(
        new FileInputStream("date.bin"))) {
    
    int nr = in.readInt();         // 42
    double d = in.readDouble();    // 3.14
    String nume = in.readUTF();    // "Ana"
    boolean b = in.readBoolean();  // true
    
} catch (IOException e) {
    e.printStackTrace();
}
```

### Available Methods

| Type | Writing | Reading |
|-----|---------|--------|
| `int` | `writeInt(int v)` | `int readInt()` |
| `long` | `writeLong(long v)` | `long readLong()` |
| `double` | `writeDouble(double v)` | `double readDouble()` |
| `float` | `writeFloat(float v)` | `float readFloat()` |
| `boolean` | `writeBoolean(boolean v)` | `boolean readBoolean()` |
| `char` | `writeChar(int v)` | `char readChar()` |
| `String` | `writeUTF(String s)` | `String readUTF()` |

---

## `BufferedInputStream` / `BufferedOutputStream` — Byte Buffering

Similar to `BufferedReader`/`BufferedWriter`, but for byte streams (not characters).

```java
// Buffered writing — much faster than direct FileOutputStream
try (BufferedOutputStream out = new BufferedOutputStream(
        new FileOutputStream("mare.bin"))) {
    
    for (int i = 0; i < 1_000_000; i++) {
        out.write(i % 256);  // written to buffer, not directly to disk
    }
    
} // automatic flush upon closing

// Buffered reading
try (BufferedInputStream in = new BufferedInputStream(
        new FileInputStream("mare.bin"))) {
    
    int b;
    while ((b = in.read()) != -1) {
        // process byte
    }
}
```

**When to use:**
- Large binary files
- Many small read/write operations
- Critical performance

---

## `RandomAccessFile` — Random Access

Allows reading and writing **at any position** within a file, not just sequentially.

### Opening

```java
// "r" — read only
// "rw" — read and write
RandomAccessFile raf = new RandomAccessFile("date.bin", "rw");
```

### Cursor (`file pointer`)

The file is treated as an array of bytes. The cursor indicates the current position (byte index).

```java
long pos = raf.getFilePointer();  // current position (0 at start)
raf.seek(100);                     // moves cursor to byte 100
raf.skipBytes(20);                 // skips 20 bytes
```

### Reading/Writing

`RandomAccessFile` implements `DataInput` and `DataOutput`, so it has all the `read*()` and `write*()` methods:

```java
RandomAccessFile raf = new RandomAccessFile("student.bin", "rw");

// Writing at position 0
raf.writeUTF("Ana");
raf.writeInt(20);

// Reading from the beginning
raf.seek(0);
String nume = raf.readUTF();
int varsta = raf.readInt();

raf.close();
```

### Practical Example — BMP Image

The width of a BMP image is stored in 4 bytes, starting at byte 18:

```java
RandomAccessFile img = new RandomAccessFile("photo.bmp", "r");
img.seek(18);                    // moves cursor to byte 18
int width = img.readInt();       // reads width (4 bytes)
width = Integer.reverseBytes(width);  // BMP is little-endian
System.out.println("Width: " + width + " pixels");
img.close();
```

---

## `ByteBuffer` and Endianness

### Big-endian vs. Little-endian

When an integer (`int` = 4 bytes) is written to a binary file, the byte order can vary:

| Value | Big-endian (Java default) | Little-endian (Windows, BMP) |
|---------|----------------------------|------------------------------|
| `720` (decimal) | `00 00 02 D0` | `D0 02 00 00` |

**Java reads/writes in big-endian by default.** Windows files (BMP, executables) use little-endian.



### `ByteBuffer` — Conversion

```java
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

RandomAccessFile raf = new RandomAccessFile("date.bin", "r");
byte[] bytes = new byte[4];
raf.read(bytes);  // reads 4 bytes

// Converts to little-endian int
ByteBuffer buffer = ByteBuffer.wrap(bytes);
buffer.order(ByteOrder.LITTLE_ENDIAN);
int value = buffer.getInt();

System.out.println(value);
raf.close();
```

### Alternative — `Integer.reverseBytes()`

For `int`, `short`, `long`:

```java
int valueBigEndian = raf.readInt();
int valueLittleEndian = Integer.reverseBytes(valueBigEndian);
```

---

## `try-with-resources` — Automatic Resource Management

Introduced in **Java 7**, it eliminates the need for a `finally` block to close resources.

### Old Syntax (without try-with-resources)

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("date.txt"));
    String linie = br.readLine();
    // processing
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### New Syntax (try-with-resources)

```java
try (BufferedReader br = new BufferedReader(new FileReader("date.txt"))) {
    String linie = br.readLine();
    // processing
} catch (IOException e) {
    e.printStackTrace();
}
// br.close() is called AUTOMATICALLY
```

### Multiple Resources

```java
try (BufferedReader in = new BufferedReader(new FileReader("input.txt"));
     BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"))) {
    
    String linie;
    while ((linie = in.readLine()) != null) {
        out.write(linie.toUpperCase());
        out.newLine();
    }
    
} // both close automatically, in reverse order (out, then in)
```

### The Condition — The `AutoCloseable` Interface

Any class that implements `AutoCloseable` (or `Closeable`, which extends `AutoCloseable`) can be used:

- `BufferedReader`, `BufferedWriter`
- `FileReader`, `FileWriter`
- `FileInputStream`, `FileOutputStream`
- `RandomAccessFile`
- `ObjectInputStream`, `ObjectOutputStream`
- `DataInputStream`, `DataOutputStream`

---

## Comparison — When to Use Each Stream

| Stream | Purpose | Example Usage |
|------|------|---------------------|
| `FileReader` / `FileWriter` | Small text files, character by character | Simple logs |
| `BufferedReader` / `BufferedWriter` | Text files, line by line (recommended) | CSV, configurations, processing output |
| `FileInputStream` / `FileOutputStream` | Binary files, byte by byte | Copying files |
| `BufferedInputStream` / `BufferedOutputStream` | Large binary files, performance | Images, video, archives |
| `DataInputStream` / `DataOutputStream` | Formatted binary primitive data | Custom data structures |
| `ObjectInputStream` / `ObjectOutputStream` | Complete Java objects | Saving app state, cache |
| `RandomAccessFile` | Access at specific positions | BMP headers, simple databases |

---

## What You Will Do in Laboratory 09

1. **Mandatory Exercise:** Full serialization with `Serializable`, `serialVersionUID`, `transient`.
2. **Formatted Read/Write:** `DataInputStream`/`DataOutputStream` for custom structures.
3. **Random Access:** `RandomAccessFile` for processing BMP images or file headers.
4. **Buffering for Performance:** Speed comparison with/without `BufferedInputStream`.
5. **`try-with-resources`:** Refactoring old code using modern syntax.

---

## Additional Resources

- **Oracle Java Tutorials — I/O:** [https://docs.oracle.com/javase/tutorial/essential/io/](https://docs.oracle.com/javase/tutorial/essential/io/)
- **Serialization Guide:** [https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/](https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/)
- **RandomAccessFile JavaDoc:** [https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/RandomAccessFile.html](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/RandomAccessFile.html)