package org.example.profession;

import org.example.profession.profession.Profession;
import org.example.profession.profession.ProfessionService;
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
                    System.out.println("listP - list all professions");
                    System.out.println("addP - add a new profession");
                    System.out.println("deleteP - delete a profession");
                    break;
                case "listP":
                    professionService.getAllProfessions().forEach(System.out::println);
                    break;
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
                case "deleteP":
                    System.out.println("enter profession uuid: ");
                    UUID professionUuid = null;
                    try {
                        professionUuid = UUID.fromString(scanner.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println("!wrong uuid of profession");
                        break;
                    }
                    if(professionService.existsProfessionById(professionUuid)){
                        professionService.deleteProfessionById(professionUuid);
                        System.out.println("Successfully deleted profession!");
                    }
                    else{
                        System.out.println("profession not found!");
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

