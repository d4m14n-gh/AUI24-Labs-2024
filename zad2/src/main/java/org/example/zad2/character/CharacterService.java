package org.example.zad2.character;

import org.example.zad2.profession.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Character addCharacter(Character character) {
        return characterRepository.save(character);
    }

    public void deleteCharacterById(UUID id) {
        characterRepository.deleteById(id);
    }

    public List<Character> getCharacterByCharacterClass(Profession profession) {
        return characterRepository.findCharacterByProfession(profession);
    }

    public List<Character> getCharacter() {
        return characterRepository.findAll();
    }
}
