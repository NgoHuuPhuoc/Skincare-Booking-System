package com.skincare.skincareapp.repository;

import com.skincare.skincareapp.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // 🟡 Tự động sinh SQL: SELECT * FROM appointment WHERE appointment_time = ? AND branch_id = ?
    boolean existsByAppointmentTimeAndBranchId(LocalDateTime appointmentTime, Long branchId);
}
