package org.example.zad3.character;

import org.example.zad3.profession.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public void addCharacter(Character character) {
        characterRepository.save(character);
    }

    public void deleteCharacterById(UUID id) {
        characterRepository.deleteById(id);
    }

    public Optional<Character> getCharacterById(UUID id) {
        return characterRepository.findById(id);
    }

    public List<Character> getCharacterByCharacterClass(Profession profession) {
        return characterRepository.findCharacterByProfession(profession);
    }

    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }
}

