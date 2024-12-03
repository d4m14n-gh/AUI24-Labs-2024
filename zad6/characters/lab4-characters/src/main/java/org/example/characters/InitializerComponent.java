package org.example.characters;

import jakarta.annotation.PostConstruct;
import org.example.characters.character.Character;
import org.example.characters.character.CharacterService;
import org.example.characters.dto.Mapper;
import org.example.characters.dto.profession.ProfessionsDto;
import org.example.characters.profession.Profession;
import org.example.characters.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class InitializerComponent {
//    private final ProfessionService professionService;
//    private final CharacterService characterService;
//
//    @Autowired
//    @Qualifier("library")
//    private RestTemplate restTemplate;
//
//    static class UUIdGenerator {
//        final static List<UUID> uuids = new ArrayList<>();
//        static final String FILE_NAME = "ids.txt";
//        static int counter = 0;
//        static UUID get(){
//            if (counter < uuids.size()){
//                counter++;
//                return uuids.get(counter-1);
//            }
//            return UUID.randomUUID();
//        }
//        static void load(){
//            try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_NAME))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    uuids.add(UUID.fromString(line));
//                }
//            } catch (IOException e) {
//                System.err.println("error reading: " + e.getMessage());
//            }
//        }
//        static void generateAndSave(int cnt){
//            for (int i = 0; i < cnt; i++){
//                uuids.add(UUID.randomUUID());
//            }
//            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
//                for (UUID id : uuids) {
//                    writer.write(id.toString());
//                    writer.newLine();
//                }
//            } catch (IOException e) {
//                System.err.println("save error: " + e.getMessage());
//            }
//        }
//    }
//    public void addExampleCharacterCollection(Random rng, Profession profession) {
//        final int characterCount = rng.nextInt(5);
//        final String[] randomNames = {
//                "Alice",
//                "Bob",
//                "Charlie",
//                "Diana",
//                "Edward",
//                "Fiona",
//                "George",
//                "Hannah",
//                "Ivan",
//                "Julia"
//        };
//
//        for (int i = 0; i < characterCount; i++) {
//            String newName = randomNames[rng.nextInt(randomNames.length)];
//            int newLevel = rng.nextInt(1, 11);
//            UUID uniqueId = UUIdGenerator.get();
//
//            Character newCharacter = new Character();
//            newCharacter.setUuid(uniqueId);
//            newCharacter.setName(newName);
//            newCharacter.setLevel(newLevel);
//            newCharacter.setProfession(profession);
//
//            characterService.addCharacter(newCharacter);
//        }
//    }
//    public void addExampleCategoryCollection(Random rng){
//        for (Profession profession: professionService.getAllProfessions())
//            addExampleCharacterCollection(rng, profession);
//    }
//
//
//    @Autowired
//    public InitializerComponent(CharacterService characterService, ProfessionService professionService) {
//        this.characterService = characterService;
//        this.professionService = professionService;
//    }
//
//    @PostConstruct
//    public void init() throws Exception {
//        Random rng = new Random(1234);
//        //UUIdGenerator.generateAndSave(1000);
//        UUIdGenerator.load(); //generateAndSave(1000);
//        while (true){
//            try{
//                List<LinkedHashMap> list = restTemplate.getForEntity("/api/professions", List.class).getBody();
//                for(var profession: list)
//                    professionService.addProfession(new Profession( UUID.fromString(profession.get("id").toString()), profession.get("name").toString(), new ArrayList<Character>() ));
//                break;
//            }
//            catch (Exception e){
//                Thread.sleep(1000);
//            }
//        }
//        addExampleCategoryCollection(rng);
//    }
}
