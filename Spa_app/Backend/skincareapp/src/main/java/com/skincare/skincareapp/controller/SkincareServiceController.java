package com.skincare.skincareapp.controller;

import com.skincare.skincareapp.entity.ServiceEntity;
import com.skincare.skincareapp.service.SkincareServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin // Cho phép gọi từ frontend nếu có
public class SkincareServiceController {

    private final SkincareServiceService service;

    public SkincareServiceController(SkincareServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return service.getAllServices();
    }
}
