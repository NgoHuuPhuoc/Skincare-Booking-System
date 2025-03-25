package com.example.skincarebooking.models;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    private Long id;
    private User user;
    private Service service;
    private LocalDateTime appointmentTime;
}
