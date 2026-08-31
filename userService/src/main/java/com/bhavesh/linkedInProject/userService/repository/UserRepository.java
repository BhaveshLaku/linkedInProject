package com.bhavesh.linkedInProject.userService.repository;

import com.bhavesh.linkedInProject.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
