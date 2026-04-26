#### 1.1 — A list of at least 10 possible actions/queries in the system


for *Pet Clinic* topic:

1. Register Pet & Owner 
2. Update Pet Information 
3. Schedule Appointment
4. Manage Appointment State (Check-in/Cancel/Undo)
5. Vet: Add Medical Notes & Diagnosis
6. Staff: Process Payment & Finalize Consultation
7. View Vet Schedule (by Vet ID)
8. Most Popular Services Statistics
9. Search/List Consultations (Sorted by Price)
10. Remove Pet Record
11. Show All Pets
12. Show All Owners
13. Show All Vets
14. Show All Appointments



#### 1.2 — A list of at least 8 domain object types
Pet, Person, Owner, Vet, Staff, MedicalService, DiscountedService, EmergencyService, StandardService, Appointment, Consultation


### 2. Java Implementation

#### 2.1 — Classes and OOP

- 11 classes in model 
- Used only private and protected with getters/setters where necessary 
- Used `toString()`, `equals()`, and `hashCode()` methods in Pet and Person classes. 
- Person (Abstract) → Staff → Vet
- MedicalService (Abstract) → StandardService, DiscountedService, EmergencyService.
- Abstract Classes: Person (defines getDescription()) and MedicalService (defines getPrice()).
- Immutable Class: Consultation (all final fields, no setters, initialized via constructor).
- Custom Exceptions: Handled via AppointmentIsAlreadyFinalException, CannotCancelFinalAppointmentException, and CannotRevertInitialAppointmentStateException.

#### 2.2 — Collections
Map for Indexing: Used in PetService, OwnerService, and AppointmentService (e.g., Map<String, Pet>) to allow fast lookup of objects by their unique ID String.
Map for Grouping: Used in AppointmentService.showServiceStatistics() to group and count appointments by ServiceType.
Sorted Collection: Implemented in BillingService.getConsultationsSortedByPrice(), which uses a Comparator to sort a List of records.
List for History: The Appointment class uses a List<State> to maintain a stack of states, enabling the "Undo" functionality.

#### 2.3 — Services

Singleton Pattern: All services (PetService, OwnerService, etc.) are implemented as Singletons.
Service Operations: Each service exposes standardized methods for add, delete, findById, and list all.
Main Class: The Main.java class demonstrates 14 actions.

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


