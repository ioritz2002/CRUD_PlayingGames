package ioritz.demo.repository;

import ioritz.demo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    public Game findByName(String name);
}
