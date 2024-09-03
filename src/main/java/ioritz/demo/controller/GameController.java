package ioritz.demo.controller;

import ioritz.demo.model.Game;
import ioritz.demo.model.GameDTO;
import ioritz.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameDTO> getAllGames() {
        return gameService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        Game game = gameService.findById(id).orElse(null);

        if(game == null){
            return ResponseEntity.notFound().build();
        }

        GameDTO gameDTO = convertToDto(game);

        return ResponseEntity.ok(gameDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GameDTO> findByName(@PathVariable String name) {
        Game game = gameService.findByName(name);

        if(game == null){
            return ResponseEntity.notFound().build();
        }

        GameDTO gameDTO = convertToDto(game);
        return ResponseEntity.ok(gameDTO);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.save(game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
    }

    private GameDTO convertToDto(Game game) {
        return new GameDTO(
                game.getId(),
                game.getName(),
                game.getState(),
                game.getSaga().getName()
        );
    }
}
