package com.pao.project.etapa2;

import com.pao.project.etapa2.model.*;
import com.pao.project.etapa2.repository.*;
import com.pao.project.etapa2.service.ClinicService;
import com.pao.project.etapa2.service.AuditService;
import com.pao.project.etapa2.util.DatabaseConnection;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        AuditService audit = AuditService.getInstance();
        OwnerRepository ownerRepo = new OwnerRepository();
        VetRepository vetRepo = new VetRepository();
        PetRepository petRepo = new PetRepository();
        AppointmentRepository appointmentRepo = new AppointmentRepository();
        ConsultationRepository consultationRepo = new ConsultationRepository();
        ClinicService clinicService = ClinicService.getInstance();

        System.out.println("=== VETERINARY CLINIC JDBC — DEMO ===\n");

        // ---- Action 1: Register Owner ----
        Owner o1 = new Owner(1L, "John", "Doe");
        ownerRepo.save(o1);
        audit.log("register_owner");
        System.out.println("1. Owner registered successfully: " + o1.getFirstName() + " " + o1.getLastName());

        // ---- Action 2: Register Vet ----
        Vet v1 = new Vet(1L, "Alice", "Smith");
        vetRepo.save(v1);
        audit.log("register_vet");
        System.out.println("2. Veterinary Doctor registered successfully: Dr. " + v1.getFirstName() + " " + v1.getLastName());

        // ---- Action 3: Register Pet Patients ----
        Pet p1 = new Pet(1L, "Buddy", "Golden Retriever", o1);
        Pet p2 = new Pet(2L, "Luna", "Cat", o1);
        petRepo.save(p1);
        petRepo.save(p2);
        audit.log("register_pet");
        System.out.println("3. Patients registered in system: " + p1.getPetName() + ", " + p2.getPetName());

        // ---- Action 4: Schedule Appointments ----
        Appointment app1 = new Appointment(1L, p1, v1, ServiceType.VACCINATION, State.SCHEDULED,
                LocalDateTime.now(), "Annual Booster", "");
        appointmentRepo.save(app1);
        audit.log("schedule_appointment");
        System.out.println("4. Appointment booked successfully.");

        // ---- Action 5: Display Appointments & Update States ----
        List<Appointment> allApps = appointmentRepo.findAll();
        System.out.println("5. Display appointments (" + allApps.size() + "):");
        for (Appointment a : allApps) {
            System.out.println(String.format("ID: %d | Pet ID: %d | State: %s", a.getId(),
                    a.getPet().getId(), a.getState()));
        }
        app1.nextState();
        appointmentRepo.update(app1);
        audit.log("list_all_appointments");

        // ---- Action 6: Find Pet By ID ----
        System.out.println("\n6. Find pet by id...");
        petRepo.findById(p1.getId()).ifPresent(pet ->
                System.out.println("[Database Check] Successfully found patient in DB: " + pet.getPetName())
        );
        audit.log("find_pet_by_id");

        // ---- Action 6: Add Vet Medical Notes ----
        app1.setMedicalNote(app1.getClientReason(), "Healthy condition. Diagnostic clear.");
        app1.nextState();
        appointmentRepo.update(app1);
        audit.log("add_medical_notes");
        System.out.println("7. Medical checkup notes finalized.");

        // ---- Action 7: Process Payment ----
        Consultation consultation1 = new Consultation(app1.getId(), app1.getPet(), app1.getDoctorNote(), 320.00);
        clinicService.saveAppointmentUpdateWithBilling(app1, consultation1);
        app1.nextState();
        audit.log("finalize_billing_transaction");
        System.out.println("8. Billing checkout completed.");

        // ---- Action 8: Demonstrate Rollback ----
        System.out.println("\n9. Testing database safety: Trying to charge the same bill twice...");
        try {
            clinicService.saveAppointmentUpdateWithBilling(app1, consultation1);
        } catch (SQLException e) {
            System.out.println("[ROLLBACK SUCCESSFUL] Double-billing blocked! Database changes undone safely:" + e.getMessage());
        }
        audit.log("demonstrate_rollback_exception");

        // ---- Action 9: Generate SQL Join Reports ----
        System.out.println("\n10. Fetching Relational JOIN Reports...");
        List<String> schedule = clinicService.findAppointmentsByVetId(v1.getId());
        schedule.forEach(s -> System.out.println("[JOIN #1] " + s));

        List<String> stats = clinicService.getServiceStatistics();
        stats.forEach(s -> System.out.println("[JOIN #2] " + s));

        List<String> invoice = clinicService.getPetInvoice();
        invoice.forEach(l -> System.out.println("[JOIN #3] " + l));
        audit.log("execute_relational_join_reports");

        // ---- Action 10: Remove Pet ----
        long petIdToDelete = p2.getId();
        consultationRepo.deleteByPetId(petIdToDelete);
        petRepo.delete(petIdToDelete);
        audit.log("delete_pet_record");
        System.out.println("\n11. Patient record was removed. Pet ID=" + petIdToDelete);

        System.out.println("\n=== Demo completed. Check audit.csv ===");
        DatabaseConnection.getInstance().close();
    }
}