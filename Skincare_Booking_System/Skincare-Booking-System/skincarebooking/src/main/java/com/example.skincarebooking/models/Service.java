package com.example.skincarebooking.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private Long id;
    private String name;
    private double price;
}
