package org.example.aiprojekttest.repository;

import org.example.aiprojekttest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
