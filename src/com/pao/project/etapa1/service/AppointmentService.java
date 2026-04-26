package com.pao.project.etapa1.service;

import com.pao.project.etapa1.model.Appointment;
import com.pao.project.etapa1.model.Pet;
import com.pao.project.etapa1.model.ServiceType;
import com.pao.project.etapa1.model.State;


import java.util.*;
import java.util.stream.Collectors;

public class AppointmentService {

    private final Map<String, Appointment> appointments = new HashMap<>();

    private AppointmentService(){};

    private static class Holder{
        private static final AppointmentService instance = new AppointmentService();
    }

    public static AppointmentService getInstance(){return Holder.instance;}


    // add
    public void scheduleAppointment(Appointment newApp) {
        // Check for ID duplication
        if (appointments.containsKey(newApp.getId())) {
            System.out.println("ERROR: Appointment ID already exists.");
            return;
        }

        // Check for Vet Conflict (Same Vet and Same Time)
        boolean conflict = appointments.values().stream().anyMatch(a -> a.getVet().getId().equals(newApp.getVet().getId()) && a.getDateTime().equals(newApp.getDateTime()));

        if (conflict) {
            System.out.println("ERROR: Dr. " + newApp.getVet().getLastName() + " is already booked at " + newApp.getDateTime());
            return;
        }

        appointments.put(newApp.getId(), newApp);
        System.out.println("Appointment scheduled successfully.");
    }

    // get appointment
    public Appointment getAppointmentById(String id){return appointments.get(id);}

    // find by id
    public List<Appointment> findAppointmentsByVetId(String vetId) {
        return appointments.values().stream().filter(a -> a.getVet().getId().equals(vetId)).collect(Collectors.toList());
    }

    // list all
    public List<Appointment> getAll(){
        return new ArrayList<>(appointments.values());
    }

    // delete
    public void removeAppointment(String id){appointments.remove(id);}

    // ShowMostPopularService
    public void showServiceStatistics() {
        System.out.println("\n--- Service Usage (Completed/Active Visits) ---");

        Map<ServiceType, Long> counts = appointments.values().stream().filter(a -> a.getState() != State.CANCELED).collect(Collectors.groupingBy(Appointment::getService, Collectors.counting()));

        counts.entrySet().stream().sorted(Map.Entry.<ServiceType, Long>comparingByValue().reversed().thenComparing(e -> e.getKey().name())).forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " visits"));
    }



}
