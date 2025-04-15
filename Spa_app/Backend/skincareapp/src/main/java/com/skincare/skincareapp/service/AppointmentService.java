package com.skincare.skincareapp.service;

import com.skincare.skincareapp.dto.AppointmentRequest;
import com.skincare.skincareapp.entity.*;
import com.skincare.skincareapp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;
    private final ServiceRepository serviceRepo;
    private final BranchRepository branchRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, UserRepository userRepo,
                              ServiceRepository serviceRepo, BranchRepository branchRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.serviceRepo = serviceRepo;
        this.branchRepo = branchRepo;
    }

    public Appointment createAppointment(AppointmentRequest req) {
        // Kiểm tra người dùng
        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với ID = " + req.getUserId()));

        // Kiểm tra dịch vụ
        ServiceEntity service = serviceRepo.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy service với ID = " + req.getServiceId()));

        // Kiểm tra chi nhánh
        Branch branch = branchRepo.findById(req.getBranchId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi nhánh với ID = " + req.getBranchId()));

        // ✅ Kiểm tra xem giờ này tại chi nhánh đã được đặt chưa
        boolean isDuplicate = appointmentRepo.existsByAppointmentTimeAndBranchId(
                req.getAppointmentTime(), req.getBranchId()
        );
        if (isDuplicate) {
            throw new RuntimeException("Khung giờ này tại chi nhánh đã được đặt! Vui lòng chọn khung giờ khác.");
        }

        // Nếu không trùng thì tạo mới
        Appointment appointment = Appointment.builder()
                .user(user)
                .service(service)
                .branch(branch)
                .appointmentTime(req.getAppointmentTime())
                .status("Đã đặt")
                .build();

        return appointmentRepo.save(appointment);
    }


    // ✅ Lấy toàn bộ lịch hẹn
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    // ✅ Lấy lịch theo User ID
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        return appointmentRepo.findAll().stream()
                .filter(a -> a.getUser().getId().equals(userId))
                .toList();
    }

    // ✅ Lấy lịch theo Branch ID
    public List<Appointment> getAppointmentsByBranchId(Long branchId) {
        return appointmentRepo.findAll().stream()
                .filter(a -> a.getBranch().getId().equals(branchId))
                .toList();
    }

    // ✅ Lấy lịch theo ngày
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepo.findAll().stream()
                .filter(a -> a.getAppointmentTime().toLocalDate().equals(date))
                .toList();
    }
    public List<String> getBookedTimeSlots(Long branchId, LocalDate date) {
        return appointmentRepo.findAll().stream()
                .filter(a -> a.getBranch().getId().equals(branchId))
                .filter(a -> a.getAppointmentTime().toLocalDate().equals(date))
                .map(a -> a.getAppointmentTime().toLocalTime().toString())
                .toList();
    }

}
