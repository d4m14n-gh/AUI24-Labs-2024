package org.example.zad3.controllers;

import org.example.zad3.character.Character;
import org.example.zad3.character.CharacterService;
import org.example.zad3.dto.CharacterDto;
import org.example.zad3.dto.CharacterMapper;
import org.example.zad3.dto.CharactersDto;
import org.example.zad3.profession.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class characterController {
    private final ProfessionService professionService;
    private final CharacterService characterService;
    @Autowired
    public characterController(CharacterService characterService, ProfessionService professionService) {
        this.characterService = characterService;
        this.professionService = professionService;
    }

    //characterDto
    @GetMapping("/characters/{id}")
    public ResponseEntity<CharacterDto> getCharacter(@PathVariable UUID id) {
        var character = characterService.getCharacterById(id);
        if (character.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok( CharacterMapper.toDTO(character.get()) );
        }
    }
    @PutMapping("/characters/{id}")
    public ResponseEntity<CharacterDto> addCharacter(@Request CharacterDto characterDto) {
        Character character = CharacterMapper.fromDTO(characterDto, professionService);
        characterService.addCharacter(character);
    }
//    @PatchMapping("/characters/{id}")
//    public ResponseEntity<CharacterDto> addCharacterById(@PathVariable UUID id) {
//        var character = characterService.getCharacterById(id);
//        if (character.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        else {
//            return ResponseEntity.ok( CharacterMapper.toDTO(character.get()) );
//        }
//    }

    //charactersDto
    @GetMapping("/characters")
    public ResponseEntity<List<CharacterDto>> listOrders() {
        return ResponseEntity.ok( characterService.getCharacters().stream().map(CharacterMapper::toDTO).toList() );
    }
//    @PostMapping("/orders")
//    public ResponseEntity<Void> addOrder(@Request BodyOrder order) {
//
//    }
}
