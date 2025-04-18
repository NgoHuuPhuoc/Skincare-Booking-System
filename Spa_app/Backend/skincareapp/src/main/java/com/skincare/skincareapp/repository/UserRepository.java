package com.skincare.skincareapp.repository;

import com.skincare.skincareapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
}
