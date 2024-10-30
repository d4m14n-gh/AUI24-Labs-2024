package org.example.zad3;

import jakarta.annotation.PostConstruct;
import org.example.zad3.character.Character;
import org.example.zad3.character.CharacterService;
import org.example.zad3.profession.Profession;
import org.example.zad3.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class InitializerComponent {
    static class UUIdGenerator {
        final static List<UUID> uuids = new ArrayList<>();
        static final String FILE_NAME = "ids.txt";
        static int counter = 0;
        static UUID get(){
            if (counter < uuids.size()){
                counter++;
                return uuids.get(counter-1);
            }
            return UUID.randomUUID();
        }
        static void load(){
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    uuids.add(UUID.fromString(line));
                }
            } catch (IOException e) {
                System.err.println("error reading: " + e.getMessage());
            }
        }
        static void generateAndSave(int cnt){
            for (int i = 0; i < cnt; i++){
                uuids.add(UUID.randomUUID());
            }
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
                for (UUID id : uuids) {
                    writer.write(id.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("save error: " + e.getMessage());
            }
        }
    }
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
            UUID uniqueId = UUIdGenerator.get();

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
            UUID uniqueId = UUIdGenerator.get();

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
        UUIdGenerator.load(); //generateAndSave(1000);
        addExampleCategoryCollection(rng);
    }
}
