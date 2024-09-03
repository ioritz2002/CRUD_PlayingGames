package ioritz.demo.service;

import ioritz.demo.model.Game;

import java.util.List;
import java.util.Optional;

import ioritz.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    public Game findByName(String name){
        return gameRepository.findByName(name);
    }

}
