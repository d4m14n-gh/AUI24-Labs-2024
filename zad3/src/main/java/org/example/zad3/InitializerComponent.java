package org.example.zad3;

import jakarta.annotation.PostConstruct;
import org.example.zad3.character.Character;
import org.example.zad3.character.CharacterService;
import org.example.zad3.profession.Profession;
import org.example.zad3.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class InitializerComponent {
    public void addExampleCharacterCollection(Random rng, Profession characterClass) {
        final int characterCount = rng.nextInt(5);
        final String[] randomNames = {
                "Alice",
                "Bob",
                "Charlie",
                "Diana",
                "Edward",
                "Fiona",
                "George",
                "Hannah",
                "Ivan",
                "Julia"
        };

        for (int i = 0; i < characterCount; i++) {
            String newName = randomNames[rng.nextInt(randomNames.length)];
            int newLevel = rng.nextInt(1, 11);
            UUID uniqueId = UUID.randomUUID();

            Character newCharacter = new Character();
            newCharacter.setUuid(uniqueId);
            newCharacter.setName(newName);
            newCharacter.setLevel(newLevel);
            newCharacter.setProfession(characterClass);

            characterService.addCharacter(newCharacter);
        }
    }
    public void addExampleCategoryCollection(Random rng){
        final String[] charactersClassNamesList = {
                "Warrior",
                "Mage",
                "Rogue",
                "Cleric",
                "Paladin",
                "Ranger",
                "Bard",
                "Monk",
                "Druid",
                "Necromancer"
        };

        for (String characterClassName : charactersClassNamesList){
            int newUnlockLevel = rng.nextInt(20, 101);
            UUID uniqueId = UUID.randomUUID();

            Profession newProfession = new Profession();
            newProfession.setUuid(uniqueId);
            newProfession.setUnlockLevel(newUnlockLevel);
            newProfession.setName(characterClassName);

            characterClassService.addProfession(newProfession);
            addExampleCharacterCollection(rng, newProfession);
        }
    }


    private final CharacterService characterService;
    private final ProfessionService characterClassService;

    @Autowired
    public InitializerComponent(CharacterService characterService, ProfessionService characterClassService) {
        this.characterService = characterService;
        this.characterClassService = characterClassService;
    }

    @PostConstruct
    public void init() throws Exception {
        Random rng = new Random(1234);
        addExampleCategoryCollection(rng);
    }
}
