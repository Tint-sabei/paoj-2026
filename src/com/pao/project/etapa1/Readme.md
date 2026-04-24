#### 1.1 — A list of at least 10 possible actions/queries in the system

Examples (for the *Library* topic):
- Add a new book to the library
- Register a new reader
- Loan a book to a reader
- Return a book
- Search for books by author
- List all books in a section
- Display a reader's loan history
- Check book availability
- Display books with the most loans
- Remove a reader from the system

for *Pet Clinic* topic: 
- add a pet to the system
- add an owner to the system
- update pet in the system
- update owner in the system 
- schedule for appointment select a service, select an available consultation slot)
- modify service and slot (by undo)
- cancel the appointment 
- pay the fee after consultation (optional)
- display services 
- display available consultation slots (date, time, doctor)
- add an appointment to the system 
- show an appointment confirmation (optional)
- remove a cancelled appointment 
- check the appointment list per doctor
- show the total fee to pay (optional)
- record the consultation info (medical info + fee)
- display service with the most appointments 
- display the consultation history (medical info + fee) by pet id 

Extra 
- find owner 


#### 1.2 — A list of at least 8 domain object types

Examples (for the *Library* topic):
`Book`, `Author`, `Reader`, `Section`, `Loan`, `Library`, `Copy`, `Reservation`

for the *Pet Clinic* topic: 
`Pet`, `Owner`, `Vet`, `Service`, `Process`, `Appointment`, `Consultation`, `Payment` 
---

### 2. Java Implementation

#### 2.1 — Classes and OOP

- [ ] At least **8 classes** modeling the objects defined in point 1.
- [ ] **`private`** or **`protected`** attributes, with getters/setters where necessary.
- [ ] Overridden `toString()`, `equals()`, and `hashCode()` methods in at least **2 classes**.
- [ ] At least **one inheritance hierarchy** (`extends`) with at least **2 levels**.  
  *(e.g., `Person` → `Employee` → `Manager`, or `Person` → `Student` + `Teacher`)*
- [ ] At least **one abstract class** or **one interface** used in the hierarchy.  
  *(e.g., abstract class `Person` with abstract method `getRole()`)*
- [ ] At least **one immutable class**: `final` attributes, no setters, fully initialized in the constructor.  
  *(e.g., `ISBN`, `ProductCode`, `TransactionRecord` — a type of identifier or read-only record)*
- [ ] At least **2 custom exceptions** thrown and handled in the code.  
  *(e.g., `BookUnavailableException`, `ReaderNotFoundException`)*

#### 2.2 — Collections

- [ ] At least **2 different types of collections** (`List`, `Set`, `Map`, `Queue`, etc.).
- [ ] At least **one sorted collection** — via `Comparable` on the class or via `Comparator`.  
  *(e.g., `TreeSet<Book>` sorted by title, or `List<Student>` sorted by average grade with `Collections.sort`)*
- [ ] At least **one `Map`** used for indexing or grouping.  
  *(e.g., `Map<String, List<Book>>` — books grouped by author, `Map<String, Account>` — accounts indexed by IBAN)*

#### 2.3 — Services

- [ ] At least **2 service classes** exposing system operations.  
  *(e.g., `BookService`, `ReaderService` — each managing operations for one object type)*
- [ ] Each service implemented as a **Singleton** (private constructor + static `getInstance()` method).
- [ ] Services expose at least these operations: **add, delete, search by id/name, list all**.
- [ ] A **`Main`** class that calls **all 10 actions** defined in point 1, demonstrating full system functionality.

#### 2.4 — Organization and Quality

- [ ] Code organized into logical **sub-packages**:
  ```
  com.pao.proiect.<topic>/
  ├── model/        ← domain classes
  ├── service/      ← singleton services
  ├── exception/    ← custom exceptions
  └── Main.java
  ```
- [ ] No duplicate code — common logic extracted into methods or base classes.
- [ ] No `NullPointerException` at runtime — validate inputs in services.

---