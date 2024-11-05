package org.example.profession.controllers;

import org.example.profession.dto.Mapper;
import org.example.profession.dto.profession.ProfessionDto;
import org.example.profession.dto.profession.ProfessionsDto;
import org.example.profession.profession.Profession;
import org.example.profession.profession.ProfessionService;
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
    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }



    @GetMapping("/professions/{id}")
    public ResponseEntity<ProfessionDto> getProfession(@PathVariable("id") UUID id) {
        var profession = professionService.getProfessionById(id);
        if (profession.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok( Mapper.toDTO(profession.get()) );
        }
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
    @PatchMapping("/professions/{id}")
    public ResponseEntity<Void> patchProfession(@PathVariable("id") UUID id, @RequestBody ProfessionDto professionDto) {
        boolean existed = professionService.existsProfessionById(id);

        if (!existed) {
            return ResponseEntity.notFound().build();
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
        boolean existed = professionService.existsProfessionById(id);

        if (!existed) {
            return ResponseEntity.noContent().build();
        }
        else {
            professionService.deleteProfessionById(id);
            return ResponseEntity.ok().build();
        }
    }



    @GetMapping("/professions")
    public ResponseEntity<List<ProfessionsDto>> getProfessions() {
        return ResponseEntity.ok( Mapper.toLPDTO(professionService.getAllProfessions().stream()) );
    }
}
