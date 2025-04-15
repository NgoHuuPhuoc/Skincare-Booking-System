package com.skincare.skincareapp.controller;

import com.skincare.skincareapp.dto.AppointmentRequest;
import com.skincare.skincareapp.entity.Appointment;
import com.skincare.skincareapp.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/datlich")
@CrossOrigin
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // ✅ API POST - Đặt lịch
    @PostMapping
    public Appointment datLich(@RequestBody AppointmentRequest request) {
        return service.createAppointment(request);
    }

    // ✅ API GET - Lấy toàn bộ danh sách lịch hẹn
    @GetMapping
    public List<Appointment> layTatCaLichHen() {
        return service.getAllAppointments();
    }

    // ✅ API GET - Lấy lịch theo User ID
    @GetMapping("/user/{userId}")
    public List<Appointment> layLichTheoUser(@PathVariable Long userId) {
        return service.getAppointmentsByUserId(userId);
    }

    // ✅ API GET - Lấy lịch theo Branch ID
    @GetMapping("/branch/{branchId}")
    public List<Appointment> layLichTheoChiNhanh(@PathVariable Long branchId) {
        return service.getAppointmentsByBranchId(branchId);
    }

    // ✅ API GET - Lấy lịch theo ngày (yyyy-MM-dd)
    @GetMapping("/date")
    public List<Appointment> layLichTheoNgay(@RequestParam String date) {
        return service.getAppointmentsByDate(LocalDate.parse(date));
    }
    @GetMapping("/check")
    public List<String> layGioDaDatTheoChiNhanhVaNgay(
            @RequestParam Long branchId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return service.getBookedTimeSlots(branchId, date);
    }

}
