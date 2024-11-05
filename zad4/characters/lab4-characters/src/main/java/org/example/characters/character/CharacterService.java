package org.example.characters.character;

import org.example.characters.profession.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository repository;
    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.repository = characterRepository;
    }

    public void addCharacter(Character character) {
        repository.save(character);
    }

    public void deleteCharacterById(UUID id) {
        repository.deleteById(id);
    }

    public Optional<Character> getCharacterById(UUID id) {
        return repository.findById(id);
    }

    public List<Character> getCharacterByProfession(Profession profession) {
        return repository.findCharacterByProfession(profession);
    }

    public List<Character> getCharacters() {
        return repository.findAll();
    }
    public boolean existsCharacterById(UUID id) {
        return repository.existsById(id);
    }
}

