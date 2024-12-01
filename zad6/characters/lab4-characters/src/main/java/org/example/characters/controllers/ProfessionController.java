package org.example.characters.controllers;

import org.example.characters.character.Character;
import org.example.characters.character.CharacterService;
import org.example.characters.dto.Mapper;
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
public class ProfessionController {
    private final ProfessionService professionService;
    private final CharacterService characterService;

    @Autowired
    public ProfessionController(ProfessionService professionService, CharacterService characterService) {
        this.professionService = professionService;
        this.characterService = characterService;
    }


    @PutMapping("/professions/{id}")
    public ResponseEntity<Void> getProfession(@PathVariable("id") UUID id, @RequestBody ProfessionDto professionDto) {
        boolean existed = professionService.existsProfessionById(id);

        if (!existed) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand(id)
                    .toUri();
            professionService.addProfession(Mapper.fromDTO(professionDto, id));
            return ResponseEntity.created(location).build();
        }
        else {
            Profession profession = Mapper.fromDTO(professionDto, id);
            professionService.deleteProfessionById(id);
            professionService.addProfession(profession);
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/professions/{id}")
    public ResponseEntity<Void> deleteProfessions(@PathVariable("id") UUID id) {
        Profession profession = professionService.getProfessionById(id).orElse(null);
        if (profession != null) {
            for(var uid: characterService.getCharacterByProfession(profession).stream().map(character -> character.getUuid()).toList())
                characterService.deleteCharacterById(uid);
        }
        professionService.deleteProfessionById(id);
        return ResponseEntity.ok().build();
    }
}
