package org.example.characters.controllers;

import org.example.characters.character.Character;
import org.example.characters.character.CharacterService;
import org.example.characters.dto.Mapper;
import org.example.characters.dto.character.CharacterDto;
import org.example.characters.dto.character.CharactersDto;
import org.example.characters.dto.profession.ProfessionDto;
import org.example.characters.dto.profession.ProfessionsDto;
import org.example.characters.profession.Profession;
import org.example.characters.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CharacterController {
    private final ProfessionService professionService;
    private final CharacterService characterService;
    @Autowired
    public CharacterController(CharacterService characterService, ProfessionService professionService) {
        this.characterService = characterService;
        this.professionService = professionService;
    }



    @GetMapping("/characters/{id}")
    public ResponseEntity<CharacterDto> getCharacter(@PathVariable("id") UUID id) {
        var character = characterService.getCharacterById(id);
        if (character.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok( Mapper.toDTO(character.get()) );
        }
    }
    @PutMapping("/characters/{id}")
    public ResponseEntity<Void> getCharacter(@PathVariable("id") UUID id, @RequestBody CharacterDto characterDto) {
        boolean existed = characterService.existsCharacterById(id);
        Profession profession = professionService.getProfessionById(characterDto.getProfessionId()).orElse(null);

        if (profession == null) {
            return ResponseEntity.badRequest().build();
        }
        else if (!existed) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand(id)
                    .toUri();
            characterService.addCharacter(Mapper.fromDTO(characterDto, id, profession));
            return ResponseEntity.created(location).build();
        }
        else {
            Character character = Mapper.fromDTO(characterDto, id, profession);
            characterService.deleteCharacterById(id);
            characterService.addCharacter(character);
            return ResponseEntity.noContent().build();
        }
    }
    @PatchMapping("/characters/{id}")
    public ResponseEntity<Void> patchCharacter(@PathVariable("id") UUID id, @RequestBody CharacterDto characterDto) {
        boolean existed = characterService.existsCharacterById(id);
        Profession profession = professionService.getProfessionById(characterDto.getProfessionId()).orElse(null);

        if (profession == null) {
            return ResponseEntity.badRequest().build();
        }
        else if (!existed) {
            return ResponseEntity.notFound().build();
        }
        else {
            Character character = Mapper.fromDTO(characterDto, id, profession);
            characterService.deleteCharacterById(id);
            characterService.addCharacter(character);
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/characters/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable("id") UUID id) {
        System.out.println("xdd");
        boolean existed = characterService.existsCharacterById(id);

        if (!existed) {
            return ResponseEntity.noContent().build();
        }
        else {
            characterService.deleteCharacterById(id);
            return ResponseEntity.ok().build();
        }
    }



    @GetMapping("/characters")
    public ResponseEntity<List<CharactersDto>> getCharacters() {
        return ResponseEntity.ok( Mapper.toLCDTO(characterService.getCharacters().stream()) );
    }
    @GetMapping("/professions/{id}/characters")
    public ResponseEntity<List<CharactersDto>> getCharacters(@PathVariable("id") UUID id) {
        Profession profession = professionService.getProfessionById(id).orElse(null);
        List<Character> characters = characterService.getCharacterByProfession(profession);

        if (profession == null) {
            return ResponseEntity.badRequest().build();
        }
        else if (characters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.ok( Mapper.toLCDTO(characters.stream()) );
        }
    }
}
