package org.example.aiprojekttest.repository;

import org.example.aiprojekttest.model.Roast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoastRepository extends JpaRepository<Roast, Long> {
}
