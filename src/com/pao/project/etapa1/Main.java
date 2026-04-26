package com.pao.project.etapa1;

import com.pao.project.etapa1.model.*;
import com.pao.project.etapa1.service.*;
import com.pao.project.etapa1.exceptions.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Validate numeric input for id
    public static int getValidInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    public static void main(String[] args) {
        // Services
        PetService petService = PetService.getInstance();
        OwnerService ownerService = OwnerService.getInstance();
        AppointmentService appointmentService = AppointmentService.getInstance();
        BillingService billingService = BillingService.getInstance();

        // Data
        Owner o1 = new Owner("1", "John", "Doe");
        Owner o2 = new Owner("2", "Jane", "Smith");
        ownerService.addOwner(o1);
        ownerService.addOwner(o2);

        Pet p1 = new Pet("1", "Buddy", "Golden Retriever", o1);
        Pet p2 = new Pet("2", "Luna", "Cat", o1);
        Pet p3 = new Pet("3", "Rex", "German Shepherd", o2);
        petService.addPet(p1);
        petService.addPet(p2);
        petService.addPet(p3);

        List<Vet> clinicVets = new ArrayList<>();
        Vet v1 = new Vet("1", "Alice", "Smith");
        Vet v2 = new Vet("2", "Bob", "Brown");
        Vet v3 = new Vet("3", "Charlie", "White");
        clinicVets.add(v1);
        clinicVets.add(v2);
        clinicVets.add(v3);

        appointmentService.scheduleAppointment(new Appointment("1", p1, v1, ServiceType.VACCINATION, State.SCHEDULED, LocalDateTime.now(), "Booster", ""));
        appointmentService.scheduleAppointment(new Appointment("2", p2, v2, ServiceType.VACCINATION, State.SCHEDULED, LocalDateTime.now().plusDays(1), "Initial Vax", ""));
        appointmentService.scheduleAppointment(new Appointment("3", p3, v3, ServiceType.SURGERY, State.SCHEDULED, LocalDateTime.now().plusDays(2), "Hip Repair", ""));

        while (true) {
            System.out.println("\n===== Pet Clinic Management System =====");
            System.out.println("1. Register Pet & Owner");
            System.out.println("2. Update Pet Information");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Manage Appointment State (Check-in/Cancel/Undo)");
            System.out.println("5. Vet: Add Medical Notes & Diagnosis");
            System.out.println("6. Staff: Process Payment & Finalize Consultation");
            System.out.println("7. View Vet Schedule (by Vet ID)");
            System.out.println("8. Most Popular Services Statistics");
            System.out.println("9. Search/List Consultations (Sorted by Price)");
            System.out.println("10. Remove Pet Record");
            System.out.println("11. Show All Pets");
            System.out.println("12. Show All Owners");
            System.out.println("13. Show All Vets");
            System.out.println("14. Show All Appointments");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int option = getValidInt();

            switch (option) {
                case 1: // register
                    System.out.print("Owner ID: ");
                    String ownerId = scanner.nextLine();
                    Owner foundOwner = ownerService.findById(ownerId);

                    if (foundOwner == null) {
                        System.out.println("1. Register New Owner | 2. Cancel");
                        if (getValidInt() == 1) {
                            System.out.print("First Name: "); String firstName = scanner.nextLine();
                            System.out.print("Last Name: "); String lastName = scanner.nextLine();
                            foundOwner = new Owner(ownerId, firstName, lastName);
                            ownerService.addOwner(foundOwner);
                        } else break;
                    }

                    System.out.print("Pet ID: ");
                    String petId = scanner.nextLine();

                    if (petService.findPetById(petId) != null) {
                        System.out.println("ERROR: Pet ID " + petId + " already exists!");
                        break;
                    }

                    System.out.print("Pet Name: ");
                    String petName = scanner.nextLine();
                    System.out.print("Species: ");
                    String petSpecies = scanner.nextLine();

                    petService.addPet(new Pet(petId, petName, petSpecies, foundOwner));
                    break;

                case 2: // Update Pet Info
                    System.out.print("Enter Pet ID: ");
                    String updateId = scanner.nextLine();
                    Pet existingPet = petService.findPetById(updateId);
                    if (existingPet == null) {
                        System.out.println("Pet not found.");
                        break;
                    }

                    System.out.print("New Name (Current: " + existingPet.getPetName() + "): ");
                    String newName = scanner.nextLine();
                    System.out.print("New Species (Current: " + existingPet.getSpecie() + "): ");
                    String newSpecies = scanner.nextLine();

                    String currentOwnerId = "None";
                    if (existingPet.getOwner() != null) {
                        currentOwnerId = existingPet.getOwner().getId();
                    }
                    System.out.print("Enter New Owner ID (Current: " + currentOwnerId + "): ");
                    Owner newOwner = ownerService.findById(scanner.nextLine());

                    petService.updatePet(updateId, newName, newSpecies, newOwner);
                    break;

                case 3: // Schedule Appointment
                    System.out.print("Appointment ID: ");
                    String appId = scanner.nextLine();

                    System.out.print("Pet ID: ");
                    Pet pet = petService.findPetById(scanner.nextLine());
                    if (pet == null) {
                        System.out.println("Pet not found!");
                        break;
                    }

                    // 1. Find vet
                    System.out.print("Enter Vet ID (1, 2, or 3): ");
                    String vId = scanner.nextLine();
                    Vet selectedVet = null;
                    for (Vet vet : clinicVets) {
                        if (vet.getId().equals(vId)) {
                            selectedVet = vet;
                            break;
                        }
                    }

                    if (selectedVet == null) {
                        System.out.println("Vet not found!");
                        break;
                    }

                    // 2. Select Service
                    System.out.println("Service: 1.General 2.Vaccination 3.Surgery 4.Grooming");
                    int chosenService = getValidInt();
                    if (chosenService < 1 || chosenService > 4) {
                        System.out.println("Invalid service type.");
                        break;
                    }
                    ServiceType service = ServiceType.values()[chosenService - 1];

                    // 3. Insert date and time
                    System.out.print("Enter Date and Time (dd-MM-yyyy HH:mm): ");
                    String dateInput = scanner.nextLine();
                    LocalDateTime appTime;
                    try {
                        appTime = LocalDateTime.parse(dateInput, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                    } catch (Exception e) {
                        System.out.println("Invalid format! Defaulting to current time.");
                        appTime = LocalDateTime.now();
                    }

                    // 4. Create and Schedule
                    Appointment newApp = new Appointment(appId, pet, selectedVet, service, State.SCHEDULED, appTime, "Checkup", "");
                    appointmentService.scheduleAppointment(newApp);

                    // 5. Show
                    System.out.println("\nAppointment Scheduled!");
                    System.out.println("Time: " + appTime.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    System.out.println("Vet: Dr. " + selectedVet.getLastName());

                    break;

                case 4: // Manage appointment state
                    System.out.print("Enter Appointment ID: ");
                    Appointment app = appointmentService.getAppointmentById(scanner.nextLine());
                    if (app == null) { System.out.println("Not found."); break; }
                    System.out.println("Current State: " + app.getState());
                    System.out.print("a. Check-in | b. Cancel | c. Undo: ");
                    String state = scanner.nextLine();
                    try {
                        if (state.equalsIgnoreCase("a")) app.nextState();
                        else if (state.equalsIgnoreCase("b")) app.cancel();
                        else if (state.equalsIgnoreCase("c")) app.undoState();
                    } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
                    break;

                case 5: // Vet: Add Notes
                    System.out.print("Enter Appointment ID: ");
                    Appointment vApp = appointmentService.getAppointmentById(scanner.nextLine());
                    if (vApp == null) break;

                    if (vApp.getState() != State.CHECKED_IN) {
                        System.out.println("Error: Patient must be CHECKED_IN before a Vet can add notes.");
                        break;
                    }

                    System.out.print("Doctor Note: ");
                    vApp.setMedicalNote(vApp.getClientReason(), scanner.nextLine());

                    try {
                        vApp.nextState();
                        System.out.println("Notes saved. Appointment moved to CONSULTED.");
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;

                case 6: // finalize payment and consultation
                    System.out.print("Enter Appointment ID: ");
                    Appointment scheduledApp = appointmentService.getAppointmentById(scanner.nextLine());
                    if (scheduledApp == null) break;

                    if (scheduledApp.getState() != State.CONSULTED) {
                        System.out.println("Error: Payment can only be processed for 'CONSULTED' appointments.");
                        break;
                    }

                    System.out.print("Base Fee: ");
                    double base = Double.parseDouble(scanner.nextLine());
                    System.out.println("Class: 1.Standard 2.Discount(20%) 3.Emergency(50% extra)");
                    int choiceClass = getValidInt();

                    MedicalService ms = switch(choiceClass) {
                        case 2 -> new DiscountedService(scheduledApp.getService(), base);
                        case 3 -> new EmergencyService(scheduledApp.getService(), base);
                        default -> new StandardService(scheduledApp.getService(), base);
                    };

                    double finalPrice = ms.getPrice();
                    String typeName = switch(choiceClass) {
                        case 2 -> "Discounted (20%)";
                        case 3 -> "Emergency (50% Surcharge)";
                        default -> "Standard";
                    };

                    System.out.println("Base Fee: " + base);
                    System.out.println("Service Class: " + typeName);
                    System.out.println("-----------------------");
                    System.out.println("TOTAL TO PAY: " + finalPrice);

                    billingService.addConsultation(new Consultation(scheduledApp.getPet(), scheduledApp.getDoctorNote(), finalPrice));

                    try {
                        scheduledApp.nextState();
                        System.out.println("Payment successful. Appointment closed.");
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;

                case 7:
                    System.out.print("Enter Vet ID: ");
                    appointmentService.findAppointmentsByVetId(scanner.nextLine()).forEach(System.out::println);
                    break;

                case 8:
                    appointmentService.showServiceStatistics();
                    break;

                case 9:
                    for (Consultation c : billingService.getConsultationsSortedByPrice()) {
                        System.out.println(c);
                    }
                    break;

                case 10:
                    System.out.print("Pet ID to delete: "); String deletedId = scanner.nextLine();
                    billingService.deleteByPetId(deletedId);
                    petService.deletePet(deletedId);
                    break;

                case 11:
                    System.out.println("\n--- Registered Pets ---");
                    for (Pet p : petService.getAll()) {
                        System.out.println(p);
                    }
                    break;

                case 12: // Show All Owners
                    System.out.println("\n--- Registered Owners ---");
                    for (Owner o : ownerService.getAll()) {
                        System.out.println("ID: " + o.getId() + " | Name: " + o.getFirstName() + " " + o.getLastName());
                    }
                    break;

                case 13:
                    System.out.println("\n--- Clinic Vets ---");
                    for (Vet v : clinicVets) {
                        System.out.println("ID: " + v.getId() + " | Dr. " + v.getLastName());
                    }
                    break;


                case 14:
                    System.out.println("\n--- Master Appointment List ---");
                    List<Appointment> allApps = appointmentService.getAll();
                    if (allApps.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        for (Appointment a : allApps){
                            System.out.println(a);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exited.");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}