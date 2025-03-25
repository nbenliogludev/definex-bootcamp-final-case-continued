package com.nbenliogludev.userauthenticationservice.repository;

import com.nbenliogludev.userauthenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author nbenliogludev
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

