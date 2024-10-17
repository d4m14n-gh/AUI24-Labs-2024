package org.example.zad2.profession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessionService {
    private final ProfessionRepository repository;
    @Autowired
    public ProfessionService(ProfessionRepository repository) {
        this.repository = repository;
    }

    public Profession addProfession(Profession profession) {
        return repository.save(profession);
    }

    public List<Profession> getAllProfessions() {
        return repository.findAll();
    }

    public Profession getProfessionById(UUID id) {
        return repository.getById(id);
    }
}
