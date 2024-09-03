package ioritz.demo.repository;

import ioritz.demo.model.Saga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SagaRepository extends JpaRepository<Saga, Long> {
    public Saga findByName(String name);
}
