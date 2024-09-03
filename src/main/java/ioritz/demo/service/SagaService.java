package ioritz.demo.service;

import ioritz.demo.model.Saga;
import ioritz.demo.repository.SagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SagaService {
    @Autowired
    private SagaRepository sagaRepository;

    public List<Saga> findAll() {
        return sagaRepository.findAll();
    }

    public Optional<Saga> findById(Long id) {
        return sagaRepository.findById(id);
    }

    public Saga save(Saga saga) {
        return sagaRepository.save(saga);
    }

    public void deleteById(Long id) {
        sagaRepository.deleteById(id);
    }
}
