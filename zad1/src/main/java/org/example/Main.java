package org.example;

import lombok.Cleanup;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {
    public static List<Character> createExampleCharacterCollection(Random rng, CharacterClass characterClass) {
        final int characterCount = rng.nextInt(10);
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
        List<Character> returnCharacterCollection = new ArrayList<>();

        for (int i = 0; i < characterCount; i++) {
            String newName = randomNames[rng.nextInt(randomNames.length)];
            int newLevel = rng.nextInt(1, 11);
            Character newCharacter = Character.builder().name(newName).level(newLevel).characterClass(characterClass).build();
            returnCharacterCollection.add(newCharacter);
        }
        return returnCharacterCollection;
    }
    public static List<CharacterClass> createExampleCategoryCollection(Random rng){
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
        List<CharacterClass> returnCharacterClass = new ArrayList<>();

        for (String characterClassName : charactersClassNamesList){
            int newBaseArmor = rng.nextInt(20, 101);
            CharacterClass newCharacterClass = CharacterClass.builder().name(characterClassName).unlockLevel(newBaseArmor).build();
            List<Character> newCharacters = createExampleCharacterCollection(rng, newCharacterClass);
            newCharacterClass.setCharacters(newCharacters); //adding reference to characters
            returnCharacterClass.add(newCharacterClass);
        }
        return returnCharacterClass;
    }

    public static void main(String[] args) {
        Random rng = new Random(1234);

        //2) printing collection of categories filled with characters
        List<CharacterClass> categoryList = createExampleCategoryCollection(rng);
        System.out.println("CharacterClasses list: ");
        categoryList.forEach(
                characterClass -> {
                    System.out.println("\t"+characterClass+": ");
                    characterClass.characters.forEach(
                            character -> System.out.println("\t\t"+character)
                    );
                    System.out.println();
                }
        );


        //3) creating and printing collection of all characters -> filter=character.level > 5 sorting=character.name
        System.out.println("\nSet of characters: ");
        Set<Character> characterSet = categoryList.stream().map(CharacterClass::getCharacters).flatMap(List::stream).collect(Collectors.toSet());
        characterSet.forEach(character->System.out.println("\t"+character));


        //4) filtering and sorting collection of all characters
        System.out.println("\nFiltered and sorted characters( level>5 sorted by name ): ");
        characterSet.stream().filter(character -> character.level>5).sorted(Comparator.comparing(o -> o.name)).forEach(character->System.out.println("\t"+character));


        //5) transforming collection of all characters to DTOs
        System.out.println("\ntransformed DTO collection: ");
        List<CharacterDto> characterDtoList = characterSet.stream().map(CharacterMapper::toDTO).sorted().toList(); //todo sorting
        characterDtoList.forEach(characterDto->System.out.println("\t"+characterDto));


        //6) writing and reading
        System.out.println("\nRead set of character classes: ");
        String filePath = Path.of(System.getProperty("user.dir"), "file.bin").toString();
        try {
            @Cleanup
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(categoryList);

            @Cleanup
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            List<CharacterClass> characterClassesRead = (List<CharacterClass>) objectInputStream.readObject();
            characterClassesRead.forEach(
                    characterClass -> {
                        System.out.println("\t"+characterClass+": ");
                        characterClass.characters.forEach(
                                character -> System.out.println("\t\t"+character)
                        );
                        System.out.println();
                    }
            );
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //7) Parallel stream with custom thread pool
        ForkJoinPool customThreadPool = new ForkJoinPool(2);
        try {
            customThreadPool.submit(() ->
                    categoryList.parallelStream().map(CharacterClass::getCharacters).forEach(
                            characters -> {
                                for(Character character: characters){
                                    System.out.println(character.getCharacterClass().name+": "+character);
                                    try {
                                        Thread.sleep(1000+rng.nextInt(500));
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                    )
            ).join();
        }
        finally {
            customThreadPool.shutdown();
        }
    }
}
