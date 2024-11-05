package org.example.profession.profession;

import org.example.profession.dto.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessionService {
    @Autowired
    @Qualifier("library")
    private RestTemplate restTemplate;

    private final ProfessionRepository repository;

    @Autowired
    public ProfessionService(ProfessionRepository repository) {
        this.repository = repository;
    }

    public void addProfession(Profession profession) {
        repository.save(profession);
        restTemplate.put("/api/professions/{id}", Mapper.toDTO(profession), profession.getUuid());
    }

    public void deleteProfessionById(UUID id) {
        repository.deleteById(id);
        restTemplate.delete("/api/professions/{id}", id);
    }

    public List<Profession> getAllProfessions() {
        return repository.findAll();
    }

    public Optional<Profession> getProfessionById(UUID id) {
        return repository.findById(id);
    }

    public boolean existsProfessionById(UUID id) {
        return repository.existsById(id);
    }
}

