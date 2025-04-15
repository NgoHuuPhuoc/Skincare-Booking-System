package com.skincare.skincareapp.service;

import com.skincare.skincareapp.entity.ServiceEntity;
import com.skincare.skincareapp.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkincareServiceService {

    private final ServiceRepository repository;

    public SkincareServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<ServiceEntity> getAllServices() {
        return repository.findAll();
    }
}
