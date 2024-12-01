package org.example.characters.profession;

import org.example.characters.dto.Mapper;
import org.example.characters.dto.profession.ProfessionDto;
import org.example.characters.dto.profession.ProfessionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProfessionService {

    @Autowired
    @Qualifier("library")
    private RestTemplate restTemplate;

    @Autowired
    private ProfessionRepository repository;

    public void addProfession(Profession profession) {
        repository.save(profession);
    }

    public void deleteProfessionById(UUID id) {
        repository.deleteById(id);
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

