package com.skincare.skincareapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service") // <== thêm dòng này để map đúng bảng
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
}

