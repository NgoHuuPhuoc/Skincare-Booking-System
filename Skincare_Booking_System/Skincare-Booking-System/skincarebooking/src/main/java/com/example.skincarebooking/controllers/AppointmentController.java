package com.example.skincarebooking.controllers;

import com.example.skincarebooking.models.Appointment;
import com.example.skincarebooking.repositories.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/all")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/cancel/{id}")
    public void cancelAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
    }
}
