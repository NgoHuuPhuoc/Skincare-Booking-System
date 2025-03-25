package com.example.skincarebooking.repositories;

import com.example.skincarebooking.models.Appointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepository {
    private final List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> findAll() {
        return appointments;
    }

    public Appointment save(Appointment appointment) {
        appointments.add(appointment);
        return appointment;
    }

    public void deleteById(Long id) {
        appointments.removeIf(appointment -> appointment.getId().equals(id));
    }
}
