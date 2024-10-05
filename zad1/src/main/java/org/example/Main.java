package org.example;

import lombok.Cleanup;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {
    public static List<Character> createExampleCharacterCollection(Random rng, Profession profession) {
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
            Character newCharacter = Character.builder().name(newName).level(newLevel).profession(profession).build();
            returnCharacterCollection.add(newCharacter);
        }
        return returnCharacterCollection;
    }
    public static List<Profession> createExampleCategoryCollection(Random rng){
        final String[] professionsNamesList = {
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
        List<Profession> returnProfessionCollection = new ArrayList<>();

        for (String professionName : professionsNamesList){
            int newBaseArmor = rng.nextInt(20, 101);
            Profession newProfession = Profession.builder().name(professionName).baseArmor(newBaseArmor).build();
            List<Character> newCharacters = createExampleCharacterCollection(rng, newProfession);
            newProfession.setCharacters(newCharacters); //adding reference to characters
            returnProfessionCollection.add(newProfession);
        }
        return returnProfessionCollection;
    }

    public static void main(String[] args) {
        Random rng = new Random(1234);

        //2) printing collection of categories filled with characters
        List<Profession> categoryList = createExampleCategoryCollection(rng);
        System.out.println("Profession list: ");
        categoryList.forEach(
            profession -> {
                System.out.println("\t"+profession+": ");
                profession.characters.forEach(
                        character -> System.out.println("\t\t"+character)
                );
                System.out.println();
            }
        );


        //3) creating and printing collection of all characters -> filter=character.level > 5 sorting=character.name
        System.out.println("\nSet of characters: ");
        Set<Character> characterSet = categoryList.stream().map(Profession::getCharacters).flatMap(List::stream).collect(Collectors.toSet());
        characterSet.forEach(character->System.out.println("\t"+character));


        //4) filtering and sorting collection of all characters
        System.out.println("\nFiltered and sorted characters( level>5 sorted by name ): ");
        characterSet.stream().filter(character -> character.level>5).sorted(Comparator.comparing(o -> o.name)).forEach(character->System.out.println("\t"+character));


        //5) transforming collection of all characters to DTOs
        System.out.println("\ntransformed DTO collection: ");
        List<CharacterDto> characterDtoList = characterSet.stream().map(CharacterMapper::toDTO).sorted().toList(); //todo sorting
        characterDtoList.forEach(characterDto->System.out.println("\t"+characterDto));


        //6) writing and reading
        System.out.println("\nRead set of profession: ");
        String filePath = Path.of(System.getProperty("user.dir"), "file.bin").toString();
        try {
            @Cleanup
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(categoryList);

            @Cleanup
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            List<Profession> professionsCollectionRead = (List<Profession>) objectInputStream.readObject();
            professionsCollectionRead.forEach(
                    profession -> {
                        System.out.println("\t"+profession+": ");
                        profession.characters.forEach(
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
                categoryList.parallelStream().map(Profession::getCharacters).forEach(
                    characters -> {
                        for(Character character: characters){
                            System.out.println(character.getProfession().name+": "+character);
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
