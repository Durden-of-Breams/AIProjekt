package org.example.aiprojekttest.repository;

import org.example.aiprojekttest.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
}