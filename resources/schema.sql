DROP TABLE IF EXISTS consultations;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS vets;

CREATE TABLE owners (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name  TEXT NOT NULL,
    last_name   TEXT NOT NULL
);

CREATE TABLE vets (
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name  TEXT NOT NULL,
    last_name   TEXT NOT NULL
);

CREATE TABLE pets (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name     TEXT NOT NULL,
    specie   TEXT NOT NULL,
    owner_id INTEGER,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE appointments (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    pet_id        INTEGER,
    vet_id        INTEGER,
    service_type  TEXT NOT NULL,
    current_state TEXT NOT NULL,
    date_time     TEXT NOT NULL,
    client_reason TEXT NOT NULL,
    doctor_note   TEXT,
    FOREIGN KEY (pet_id) REFERENCES pets(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (vet_id) REFERENCES vets(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

CREATE TABLE consultations (
   id             INTEGER PRIMARY KEY AUTOINCREMENT,
   appointment_id INTEGER,
   pet_id         INTEGER,
   diagnosis      TEXT NOT NULL,
   price          REAL NOT NULL,
   date_time      TEXT DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (appointment_id) REFERENCES appointments(id)
       ON DELETE SET NULL
       ON UPDATE CASCADE,
   FOREIGN KEY (pet_id) REFERENCES pets(id)
       ON DELETE SET NULL
       ON UPDATE CASCADE
);
