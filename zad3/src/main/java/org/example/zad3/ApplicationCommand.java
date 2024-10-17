package org.example.zad3;

import org.example.zad3.character.Character;
import org.example.zad3.character.CharacterService;
import org.example.zad3.profession.Profession;
import org.example.zad3.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private ApplicationContext appContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start write: help to list commands!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    System.out.println("command line successfully exited!");
                    System.exit(SpringApplication.exit(appContext, () -> 0));
                    break;
                case "help":
                    System.out.println("help - list all commands");
                    System.out.println("exit - exit");
                    System.out.println("listCh - list all characters");
                    System.out.println("listP - list all professions");
                    System.out.println("addCh - add a new characters");
                    System.out.println("addP - add a new profession");
                    System.out.println("deleteCh - delete a character");
                    break;
                case "listCh":
                    characterService.getCharacters().forEach(character -> {
                        System.out.print(character.getProfession().getName() + ": ");
                        System.out.println(character);
                    });
                    break;
                case "listP":
                    professionService.getAllProfessions().forEach(System.out::println);
                    break;
                case "addCh": {
                    System.out.println("enter profession uuid: ");
                    String professionId = scanner.nextLine();

                    UUID professionUuid = null;
                    try{
                        professionUuid = UUID.fromString(professionId);
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("!wrong profession uuid");
                        break;
                    }
                    Profession profession = professionService.getProfessionById(professionUuid).orElse(null);
                    if (profession == null) {
                        break;
                    }

                    Character character = new Character();
                    character.setProfession(profession);
                    UUID uniqueId = UUID.randomUUID();
                    character.setUuid(uniqueId);

                    System.out.println("enter character name: ");
                    String characterName = scanner.nextLine();
                    character.setName(characterName);

                    System.out.println("enter character level: ");
                    int professionLevel = scanner.nextInt();
                    scanner.nextLine();
                    character.setLevel(professionLevel);

                    characterService.addCharacter(character);
                    System.out.println("character successfully added!");
                    break;
                }
                case "addP": {
                    Profession profession = new Profession();
                    UUID uniqueId = UUID.randomUUID();
                    profession.setUuid(uniqueId);

                    System.out.println("enter profession name: ");
                    String professionName = scanner.nextLine();
                    profession.setName(professionName);

                    System.out.println("enter profession unlock level: ");
                    int professionLevel = scanner.nextInt();
                    profession.setUnlockLevel(professionLevel);
                    scanner.nextLine();

                    professionService.addProfession(profession);
                    System.out.println("profession successfully added!");
                    break;
                }
                case "deleteCh":
                    System.out.println("enter character uuid: ");
                    try {
                        UUID uuid = UUID.fromString(scanner.nextLine());
                        characterService.deleteCharacterById(uuid);
                    } catch (IllegalArgumentException e) {
                        System.out.println("!wrong uuid of character");
                        break;
                    }
                    System.out.println("Successfully deleted character!");
                    break;
                case "":
                    break;
                default:
                    System.out.println("unknown command, write: 'help'");
                    break;
            }
        }
    }
}

