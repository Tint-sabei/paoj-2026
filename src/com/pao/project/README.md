# Individual Project — Advanced Object-Oriented Programming in Java (2026)

> **Weight in final grade: 25%**
> Each student works on an individual project, developed in two stages throughout the semester.

---

## Submission Deadlines

| Stage    | Deadline                      | Git Branch     |
|----------|-------------------------------|----------------|
| Stage I  | **Thursday, April 24, 23:59**  | `proiect-etapa1` |
| Stage II | **Thursday, June 5, 23:59**     | `proiect-etapa2` |

Submission is done by pushing to the dedicated branch in your personal fork on GitHub, before the deadline.

```bash
# Stage I
git checkout -b proiect-etapa1
git add .
git commit -m "Project Stage I: complete implementation"
git push origin proiect-etapa1

# Stage II (starting from stage1)
git checkout -b proiect-etapa2
git add .
git commit -m "Project Stage II: JDBC, audit, transactions"
git push origin proiect-etapa2
```

---

## General Scoring Conditions

- ✅ The project **compiles** without errors
- ✅ All requirements for the stage are implemented and demonstrable from `Main`
- ✅ Code is organized into packages (`com.pao.proiect.<your_topic>`)
- ✅ The branch is pushed to GitHub before the deadline

> ⚠️ Projects that do not compile or are not submitted on time **will not be graded**.

---

## Suggested Topics

| #  | Topic                                                                      |
|----|----------------------------------------------------------------------------|
| 1  | School catalog (students, subjects, teachers, grades)                      |
| 2  | Library (sections, books, authors, readers, loans)                         |
| 3  | Medical office (patients, doctors, appointments, consultations)            |
| 4  | Store inventory management (categories, products, suppliers, orders)       |
| 5  | Banking application (accounts, cards, transactions, account statements)    |
| 6  | E-learning platform (courses, users, learners, quizzes, scores)            |
| 7  | Auction system (auctions, bids, products, users)                           |
| 8  | Food delivery platform (restaurants, menus, orders, drivers, customers)    |
| 9  | Book lending platform — Bookster style (companies, users, books)           |
| 10 | E-ticketing platform (events, venues, tickets, customers)                  |

> You can also propose another topic — submit it for approval **before** starting implementation,
> via the project registration form.

---

## Stage I — OOP Modeling and Implementation

> **Deadline: Thursday, April 24, 23:59**

### 1. System Definition

Create a `README.md` file in your project folder that contains:

#### 1.1 — List of at least 10 possible actions / queries in the system

Examples (for *Library* topic):
- Add a new book to the library
- Register a new reader
- Loan a book to a reader
- Return a book
- Search for books by author
- List all books in a section
- Display a reader's loan history
- Check a book's availability
- Display books with the most loans
- Remove a reader from the system

#### 1.2 — List of at least 8 types of domain objects

Examples (for *Library* topic):
`Book`, `Author`, `Reader`, `Section`, `Loan`, `Library`, `Copy`, `Reservation`

---

### 2. Java Implementation

#### 2.1 — Classes and OOP

- [ ] At least **8 classes** that model the objects defined in point 1
- [ ] **`private`** or **`protected`** attributes, with getters/setters where necessary
- [ ] `toString()`, `equals()`, and `hashCode()` methods overridden in at least **2 classes**
- [ ] At least **one inheritance hierarchy** (`extends`) with a minimum of **2 levels**
  _(e.g., `Person` → `Employee` → `Manager`, or `Person` → `Student` + `Teacher`)_
- [ ] At least **one abstract class** or **one interface** used in the hierarchy
  _(e.g., abstract class `Person` with abstract method `getRole()`)_
- [ ] At least **one immutable class**: `final` attributes, no setters, fully initialized in constructor
  _(e.g., `ISBN`, `ProductCode`, `TransactionRecord` — an identifier or read-only record type)_
- [ ] At least **2 custom exceptions** thrown and handled in the code
  _(e.g., `BookUnavailableException`, `ReaderNotFoundException`)_

#### 2.2 — Collections

- [ ] At least **2 different types of collections** (`List`, `Set`, `Map`, `Queue`, etc.)
- [ ] At least **one sorted** — by implementing `Comparable` on a class or using `Comparator`
  _(e.g., `TreeSet<Book>` sorted by title, or `List<Student>` sorted by average with `Collections.sort`)_
- [ ] At least **one `Map`** used for indexing or grouping
  _(e.g., `Map<String, List<Book>>` — books grouped by author, `Map<String, Account>` — accounts indexed by IBAN)_

#### 2.3 — Services

- [ ] At least **2 service classes** that expose system operations
  _(e.g., `BookService`, `ReaderService` — each manages operations for one type of object)_
- [ ] Each service implemented as a **Singleton** (private constructor + static `getInstance()` method)
- [ ] Services expose at least the following operations: **add, delete, find by id/name, list all**
- [ ] A **`Main`** class that calls **all 10 actions** defined in point 1,
  demonstrating the complete functionality of the system

#### 2.4 — Organization and Quality

- [ ] Code organized into **logical sub-packages**:
  ```
  com.pao.proiect.<topic>/
  ├── model/        ← domain classes
  ├── service/      ← singleton services
  ├── exception/    ← custom exceptions
  └── Main.java
  ```
- [ ] No duplicate code — common logic extracted into methods or base classes
- [ ] No `NullPointerException` at runtime — validate inputs in services

---

## Stage II — JDBC Persistence, Transactions and Audit

> **Deadline: Thursday, June 5, 23:59**
> Stage II extends the project from Stage I — do not rewrite from scratch, but add new layers.

### 1. Relational Database + JDBC

#### 1.1 — Database Schema

- [ ] **`schema.sql`** file included in the project root, containing:
  - `CREATE TABLE` for each persisted entity
  - **Primary keys** (`PRIMARY KEY`) for all tables
  - At least **2 relationships** of type `FOREIGN KEY` between tables
  - `DROP TABLE IF EXISTS` at the beginning (for clean re-runs)

- [ ] **`db.properties`** file in `resources/` for connection configuration:
  ```properties
  db.url=jdbc:mysql://localhost:3306/paoj_proiect
  db.user=root
  db.password=your_password
  ```
  > Do not hardcode credentials directly in Java code.

#### 1.2 — Database Connection

- [ ] **`DatabaseConnection`** class implemented as a **Singleton**,
  that reads configuration from `db.properties` and exposes a reusable `Connection`

#### 1.3 — Generic Repository Interface

- [ ] Define the generic interface:
  ```java
  public interface Repository<T, ID> {
      void save(T entity);
      Optional<T> findById(ID id);
      List<T> findAll();
      void update(T entity);
      void delete(ID id);
  }
  ```
- [ ] Implement a **concrete repository** for at least **4 of the classes** from Stage I
  _(e.g., `BookRepository`, `ReaderRepository`, `LoanRepository`, `AuthorRepository`)_
- [ ] All SQL queries use **`PreparedStatement`** — not `Statement` with string concatenation
- [ ] All resources (`Connection`, `PreparedStatement`, `ResultSet`) are properly closed using **`try-with-resources`**

### 2. JDBC Transactions

- [ ] At least **one operation that affects multiple tables** must be executed within an
  **explicit JDBC transaction**, with `commit` on success and `rollback` on error:

  ```java
  connection.setAutoCommit(false);
  try {
      // operation 1 on table A
      // operation 2 on table B
      connection.commit();
  } catch (SQLException e) {
      connection.rollback();
      throw e;
  } finally {
      connection.setAutoCommit(true);
  }
  ```

  > Examples: placing a loan (inserts into `Loans` + updates `available` in `Books`),
  > processing a payment (debits one account + credits another), placing an order (inserts order + reduces stock).

### 3. Advanced Queries with JOIN

- [ ] Implement at least **3 SQL queries with JOIN** that combine data from multiple tables,
  exposed as methods in services or repositories

  > Examples (adapt to your topic):
  > - List all readers with the number of books currently loaned
  > - Display the most frequently loaned books, with the author's name
  > - Return all active loans (not returned), with reader and book details
  > - Top 5 best-selling products, with their category
  > - All orders for a customer, with total calculated from associated products

### 4. Audit Service

- [ ] Implement **`AuditService`** class (Singleton) that logs every executed action
  to a `audit.csv` file:

  ```
  action_name,timestamp
  add_book,2026-04-21T10:35:42
  search_reader,2026-04-21T10:36:01
  loan_book,2026-04-21T10:36:15
  ```

- [ ] **All 10 actions** defined in Stage I must call `AuditService` when executed
- [ ] The file is opened in **append mode** — not overwritten on each application run
- [ ] `AuditService` is **thread-safe**: use `synchronized` or `ReentrantLock`
  on the writing method

---

## Recommended Project Structure

```
src/
└── com/pao/proiect/<your_topic>/
    ├── Main.java
    ├── model/
    │   ├── Book.java
    │   ├── Reader.java
    │   └── ...
    ├── service/
    │   ├── BookService.java
    │   ├── ReaderService.java
    │   └── AuditService.java
    ├── repository/
    │   ├── Repository.java              ← generic interface
    │   ├── BookRepository.java
    │   └── ...
    ├── exception/
    │   ├── BookUnavailableException.java
    │   └── ...
    └── util/
        └── DatabaseConnection.java
resources/
    ├── schema.sql
    └── db.properties
README.md                                ← system definition (Stage I, point 1)
```

---

## Evaluation Criteria

### Stage I — 12 points out of 25

| Criterion                                                         | Points |
|-------------------------------------------------------------------|--------|
| README: 10 actions + 8 object types                               | 1p     |
| ≥8 classes with private attributes, getters/setters, `toString`   | 2p     |
| Inheritance hierarchy (≥2 levels) + abstract class / interface    | 2p     |
| Immutable class + ≥2 custom exceptions                            | 1p     |
| ≥2 different collections (one sorted) + ≥1 Map                    | 2p     |
| ≥2 Singleton services with CRUD operations in memory              | 2p     |
| Demonstrative `Main` that calls all 10 actions                    | 1p     |
| Package organization, no duplication, no NPE                      | 1p     |
| **Stage I Total**                                                 | **12p** |

### Stage II — 13 points out of 25

| Criterion                                                              | Points |
|------------------------------------------------------------------------|--------|
| Complete `schema.sql` (PK, ≥2 FK) + `db.properties` + `DatabaseConnection` singleton | 1p     |
| Generic interface `Repository<T, ID>`                                  | 1p     |
| Full CRUD (save, findById, findAll, update, delete) for ≥4 entities    | 4p     |
| All SQL queries use `PreparedStatement` + `try-with-resources`         | 2p     |
| ≥1 explicit JDBC transaction with `commit` / `rollback`                | 2p     |
| ≥3 SQL queries with `JOIN`                                             | 2p     |
| `AuditService` CSV thread-safe, called from all 10 actions             | 1p     |
| **Stage II Total**                                                     | **13p** |

> **Maximum total: 25p**
>
> Points remaining up to 25 from the total of the stages (12 + 13 = 25) can also be covered by
> [laboratory bonuses](../../../../../../README.md#-bonus-up-to-5----added-to-the-combined-laboratory--project-score)
> (+0.5% per bonus, max +5% added to the combined laboratory + project score).

---

## Frequently Asked Questions

### Can I use an ORM (Hibernate, JPA)?
No. The purpose of Stage II is understanding direct JDBC. Using an ORM means the requirement is not met.

### Can I use SQLite instead of MySQL / PostgreSQL?
Yes, SQLite is accepted and easier to configure locally (no server required). Make sure to include
the JDBC driver in the project and that `schema.sql` runs correctly with SQLite.

### Do I need a graphical user interface (GUI)?
No. A console interface (`Scanner` + text menu) is completely sufficient.

### How do I choose a topic?
Choose from the list above or propose a new topic via the registration form. Once the topic is registered,
you can only change it with the assistant's approval.

### Can I work on the same branch as the laboratories?
No. The project is submitted on separate branches (`proiect-etapa1`, `proiect-etapa2`),
distinct from the laboratory branches (`lab5`, `lab6`, etc.).