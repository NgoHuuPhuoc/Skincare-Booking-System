package com.skincare.skincareapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    private Long userId;
    private Long serviceId;
    private Long branchId;
    private LocalDateTime appointmentTime;
}
