package org.example.zad2;

import org.example.zad2.character.Character;
import org.example.zad2.character.CharacterService;
import org.example.zad2.profession.Profession;
import org.example.zad2.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private ProfessionService professionService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start write: help to list commands!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            else if (input.equals("help")) {
                System.out.println("help - list all commands");
                System.out.println("exit - exit");
                System.out.println("listCh - list all characters");
                System.out.println("listP - list all professions");
                System.out.println("addCh - add a new characters");
                System.out.println("addP - add a new profession");
                System.out.println("deleteCh - delete a character");
            }
            else if (input.equals("listCh")) {
                characterService.getCharacter().forEach(character -> {
                    System.out.print(character.getProfession().getName()+": ");
                    System.out.println(character);
                });
            }
            else if (input.equals("listP")) {
                professionService.getAllProfessions().forEach(System.out::println);
            }
            else if (input.equals("addCh")) {
                System.out.println("enter profession uuid: ");
                String professionId = scanner.nextLine();
                Profession profession = null;


                try {
                    profession = professionService.getProfessionById(UUID.fromString(professionId));
                }
                catch (Exception e) {
                    System.out.println("profession not found");
                    continue;
                }

                if (profession == null) {
                    continue;
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
            }
            else if (input.equals("addP")) {
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
            }
            else if (input.equals("deleteCh")) {
                System.out.println("enter character uuid: ");
                try {
                    UUID uuid = UUID.fromString(scanner.nextLine());
                    characterService.deleteCharacterById(uuid);
                }
                catch (Exception e) {
                    System.out.println("character not found");
                    continue;
                }
                System.out.println("Successfully deleted character!");
            }
            else{
                System.out.println("unknown command, write: 'help'");
            }
        }
    }
}

