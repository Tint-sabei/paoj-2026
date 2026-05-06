# Exercise 2 — Binary Random Access Ledger

> **Package:** `com.pao.laboratory09.exercise2`
> **Estimated Time:** ~40 min · **Automated Tests:** Yes (`Checker.java`, flat)

---

## Purpose

The bank stores transactions in a binary file with **fixed-size records** (32 bytes/transaction). 
Any transaction can be updated directly—specifically its status, at the exact position in the file—without rewriting the entire file. 
You will use `DataOutputStream` for the initial write and `RandomAccessFile` for reading and selective updating.

---

## Import from Exercise 1

```java
import com.pao.laboratory09.exercise1.TipTranzactie;
```

---

## Binary Record Format — 32 bytes per transaction

| Offset | Length | Field | Encoding |
|--------|---------|------|----------|
| 0 | 4 bytes | `id` | `int`, **little-endian** |
| 4 | 8 bytes | `amount` | `double`, **little-endian** |
| 12 | 10 bytes | `date` | ASCII, right-padded with spaces |
| 22 | 1 byte | `type` | `0`=CREDIT, `1`=DEBIT |
| 23 | 1 byte | `status` | `0`=PENDING, `1`=PROCESSED, `2`=REJECTED |
| 24 | 8 bytes | padding | zeros |

**Intermediate File:** `output/lab09_ex2.bin`

---

## Input Format

```
N
id amount date(yyyy-MM-dd) type(CREDIT|DEBIT)
... (N lines)
command*
```

Commands are read until EOF.

## Output Format

**Transaction line format:**
```
[idx] id=<id> date=<date> type=<CREDIT|DEBIT> amount=<amount:.2f> RON status=<PENDING|PROCESSED|REJECTED>
```

**Available Commands:**

| Command | Output |
|---------|--------|
| `READ idx` | Transaction line at index `idx` (0-based) |
| `UPDATE idx STATUS` | `Updated [idx]: STATUS` and modifies the status byte in the file |
| `PRINT_ALL` | All records in their current state (idx 0 → N-1) |

---

## Complete Example

```
Input:
2
1 1500.00 2024-01-15 CREDIT
2 750.50 2024-01-22 DEBIT
UPDATE 0 PROCESSED
READ 0
PRINT_ALL

Output:
Updated [0]: PROCESSED
[0] id=1 date=2024-01-15 type=CREDIT amount=1500.00 RON status=PROCESSED
[0] id=1 date=2024-01-15 type=CREDIT amount=1500.00 RON status=PROCESSED
[1] id=2 date=2024-01-22 type=DEBIT amount=750.50 RON status=PENDING
```

---

## Hints

- **Initial Write:** `DataOutputStream` wrapping a `FileOutputStream` — `writeInt`, `writeDouble` write in **big-endian**; for **little-endian**, prepare a `byte[4]` / `byte[8]` using `ByteBuffer.allocate(n).order(ByteOrder.LITTLE_ENDIAN).putInt(val).array()`
- **Date** (10 chars): write using `write(date.getBytes())` + space padding up to 10 bytes.
- **Read / Update:** `RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")` → `raf.seek(idx * 32)` → `raf.read(byte[32])` → `ByteBuffer.wrap(bytes).order(LITTLE_ENDIAN)`
- **UPDATE status:** `raf.seek(idx * 32 + 23)` → `raf.write(statusByte)` — you only update 1 byte.
- **Endianness:** Java = big-endian; `ByteBuffer.order(ByteOrder.LITTLE_ENDIAN)` handles the conversion.