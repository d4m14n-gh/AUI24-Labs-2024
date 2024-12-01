package org.example.characters;

import org.example.characters.character.Character;
import org.example.characters.character.CharacterService;
import org.example.characters.dto.profession.ProfessionDto;
import org.example.characters.profession.Profession;
import org.example.characters.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
                    System.out.println("list - list all characters by their profession");
                    System.out.println("listCh - list all characters");
                    System.out.println("addCh - add a new characters");
                    System.out.println("deleteCh - delete a character");
                    break;
                case "listCh":
                    characterService.getCharacters().forEach(character -> {
                        //System.out.print(character.getProfession().getName() + ": ");
                        System.out.println(character);
                    });
                    break;
                case "list":
                    professionService.getAllProfessions().forEach(
                            profession -> {
                                System.out.println(profession.getName()+" <"+profession.getUuid()+">: ");
                                var characters = characterService.getCharacterByProfession(profession);
                                characters.stream().map(character -> "\t- "+character.toString()).forEach(System.out::println);
                            }
                    );
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
                case "deleteCh":
                    System.out.println("enter character uuid: ");
                    UUID characterUuid = null;
                    try {
                        characterUuid = UUID.fromString(scanner.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println("!wrong uuid of character");
                        break;
                    }
                    if(characterService.existsCharacterById(characterUuid)){
                        characterService.deleteCharacterById(characterUuid);
                        System.out.println("Successfully deleted character!");
                    }
                    else{
                        System.out.println("character not found!");
                    }
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

