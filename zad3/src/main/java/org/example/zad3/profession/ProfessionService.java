package org.example.zad3.profession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessionService {
    private final ProfessionRepository repository;
    @Autowired
    public ProfessionService(ProfessionRepository repository) {
        this.repository = repository;
    }

    public void addProfession(Profession profession) {
        repository.save(profession);
    }

    public List<Profession> getAllProfessions() {
        return repository.findAll();
    }

    public Optional<Profession> getProfessionById(UUID id) {
        return repository.findById(id);
    }
}

