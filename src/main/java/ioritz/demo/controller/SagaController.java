package ioritz.demo.controller;

import ioritz.demo.model.Game;
import ioritz.demo.model.GameInSagaDTO;
import ioritz.demo.model.Saga;
import ioritz.demo.model.SagaDTO;
import ioritz.demo.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sagas")
public class SagaController {

    @Autowired
    private SagaService sagaService;

    @GetMapping
    public List<SagaDTO> getAllSagas() {
        return sagaService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SagaDTO> getSagaById(@PathVariable Long id) {
        Saga saga = sagaService.findById(id).orElse(null);

        if(saga == null) {
            return ResponseEntity.notFound().build();
        }

        SagaDTO sagaDTO = convertToDto(saga);

        return ResponseEntity.ok(sagaDTO);
    }

    @PostMapping
    public Saga createSaga(@RequestBody Saga saga) {
        return sagaService.save(saga);
    }

    @DeleteMapping("/{id}")
    public void deleteSaga(@PathVariable Long id) {
        sagaService.deleteById(id);
    }

    private SagaDTO convertToDto(Saga saga) {
        List<GameInSagaDTO> gameInSagaDTOS = saga.getGames().stream()
                .map(game -> new GameInSagaDTO(
                        game.getId(),
                        game.getName(),
                        game.getState()
                )).collect(Collectors.toList());

        return new SagaDTO(
                saga.getId(),
                saga.getName(),
                gameInSagaDTOS
        );
    }
}
